package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/usuario/locacoes/*")
public class LocacaoController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private LocacaoDAO dao;
    private ClienteDAO daoCliente;

    @Override
    public void init() {
        dao = new LocacaoDAO();
        daoCliente = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

        System.out.println("PASSEI POR: LocacaoController"); 
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();    
        if (usuario == null) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getPapel().equals("Cliente")) {
            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }
            try {
                switch (action) {
                    case "/cadastro":
                        apresentaFormCadastro(request, response);
                        break;
                    case "/insercao":
                        insere(request, response);
                        break;
                    default:
                        lista(request, response);
                        break;
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
        } else {
            erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [Cliente] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locacao> listaLocacoes = dao.getAll();
        request.getSession().setAttribute("listaLocacoes", listaLocacoes);
        request.setAttribute("listaLocacoes", listaLocacoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/logado/usuario/cliente/index.jsp");
        dispatcher.forward(request, response);
    }

    private Map<String, String> getLocadoras() {
        Map <String,String> locadoras = new HashMap<>();
        for (Locadora locadora: new LocadoraDAO().getAll()) {
            locadoras.put(locadora.getCNPJ(), locadora.getNome());
        }
        return locadoras;
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("locadoras", getLocadoras());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/locacao/formulario.jsp");
        dispatcher.forward(request, response);
    }


    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        LocalDate data = LocalDate.parse(request.getParameter("data"));
        int hora = Integer.parseInt(request.getParameter("hora"));
        LocalDateTime data_hora = LocalDateTime.of(data, LocalTime.of(hora, 0));
        // Verificar se a data é anterior à data atual
        if (data_hora.isBefore(LocalDateTime.now())) {
            Erro erros = new Erro();
            erros.add("Data da locação não pode ser anterior a data atual");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
            return;
        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Cliente cliente = daoCliente.get(usuario.getId());
        String CNPJ = request.getParameter("locadora_selecionada"); 
        Locadora locadora = new LocadoraDAO().get(CNPJ);
        
        Locacao locacao = new Locacao(cliente, locadora, data_hora);
        dao.insert(locacao);
        response.sendRedirect("lista");
    }

}