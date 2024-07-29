package org.jfm.domain.services;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.IdentificacaoPagamento;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.usecases.ClienteUseCase;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.domain.ports.Notificacao;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.domain.valueobjects.Pagamento;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;
    
    PedidoPagamentoRepository pedidoPagamentoRepository;

    ItemUseCase itemUseCase;

    ClienteUseCase clienteUseCase;

    PagamentoGateway pagamentoGateway;

    Notificacao notificacao;

    public PedidoService(PedidoRepository pedidoRepository, PedidoPagamentoRepository pedidoPagamentoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase, PagamentoGateway pagamentoGateway, Notificacao notificacao) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoPagamentoRepository = pedidoPagamentoRepository;
        this.clienteUseCase = clienteUseCase;
        this.itemUseCase = itemUseCase;
        this.pagamentoGateway = pagamentoGateway;
        this.notificacao = notificacao;
    }

    @Override
    public Pagamento criar(Pedido pedido) {
        pedido.validar();

        if (pedido.getIdCliente() != null) {
            clienteUseCase.buscarPorId(pedido.getIdCliente());
        }

        List<Item> itens = itemUseCase.listar();

        for (Item item : pedido.getItens().keySet()) {
            if (!itens.contains(item)) {
                throw new EntityNotFoundException("item " + item + " não existe");
            }
            
            item.setNome(itens.get(itens.indexOf(item)).getNome());
            item.setCategoria(itens.get(itens.indexOf(item)).getCategoria());
            item.setPreco(itens.get(itens.indexOf(item)).getPreco());
        }

        pedido.setId(UUID.randomUUID());
        pedido.setDataCriacao(Instant.now());
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);
        pedidoRepository.criar(pedido);

        Pagamento pagamento = criarPagamento(pedido);
        pedidoPagamentoRepository.criar(pedido, pagamento.id(), Instant.now());    

        return pagamento;
    };

    @Override
    public void pagamentoPedido(UUID id, Status status) {
        if (status != Status.PAGO) {
            this.notificacao.notificacaoPagamento(id, status);
            
            // TODO: throw exception?

            return;
        }
        
        Pedido pedido = this.pedidoRepository.buscarPorId(id);
        pedido.setStatus(Status.PAGO);
        this.pedidoRepository.editar(pedido);
        this.notificacao.notificacaoPagamento(id, status);
        this.notificacao.notificarCozinha(id);
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoRepository.listar();

        Collections.sort(pedidos, new Comparator<Pedido>() {
            public int compare(Pedido ped1, Pedido ped2) {
                return ped1.getDataCriacao().compareTo(ped2.getDataCriacao());
            }
        });

        return pedidos;
    };

    @Override
    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.buscarPorId(id);
    };

    @Override
    public List<Pedido> listarPorStatus(Status status) {
        List<Pedido> pedidos = pedidoRepository.listarPorStatus(status);

        Collections.sort(pedidos, new Comparator<Pedido>() {
            public int compare(Pedido ped1, Pedido ped2) {
                return ped1.getDataCriacao().compareTo(ped2.getDataCriacao());
            }
        });

        return pedidos;
    }

    @Override
    public void editar(Pedido pedido) {
        Pedido pedidoEditar = pedidoRepository.buscarPorId(pedido.getId());
        pedidoEditar.setStatus(pedido.getStatus());

        // TODO: colocar logica aqui notificacao

        pedidoRepository.editar(pedidoEditar);
    };

    private Pagamento criarPagamento(Pedido pedido) {
        Cliente cliente = this.clienteUseCase.buscarPorId(pedido.getIdCliente());
        
        int valorFinal = 0;
        StringBuilder descricao = new StringBuilder();
        
        for (Item item : pedido.getItens().keySet()) {
            descricao.append(item.getId() + " // " + item.getNome() + " // " + item.getPreco());
            valorFinal += pedido.getItens().get(item);
        }

        // TODO: utilizar forma dinamica abaixo de configurar a identificacao de pagamento
        return pagamentoGateway.criarPagamento(valorFinal, descricao.toString(), IdentificacaoPagamento.EMAIL, cliente.getEmail());
    }

}
