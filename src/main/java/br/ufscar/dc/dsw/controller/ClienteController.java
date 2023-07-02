package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.ParseException;


@WebServlet(urlPatterns = "/admin/clientes/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ClienteDAO dao;
    private UsuarioDAO daoUsuario;

    @Override
    public void init() {
        dao = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

        System.out.println("PASSEI POR: ClienteController");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();    
        if (usuario == null) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getPapel().equals("ADMIN")) {
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
                    case "/remocao":
                        remove(request, response);
                        break;
                    case "/edicao":
                        apresentaFormEdicao(request, response);
                        break;
                    case "/atualizacao":
                        atualize(request, response);
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
    		erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/lista.jsp");
        dispatcher.forward(request, response);
    }

    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.get(id);
        request.setAttribute("cliente", cliente);
        request.getSession().setAttribute("clienteEdicao", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String papel = request.getParameter("senha");

            Usuario usuario = new Usuario(email, senha, nome, papel);
            daoUsuario.insert(usuario);

            
            String CPF = request.getParameter("CPF");
            String telefone = request.getParameter("telefone");
            String sexo = request.getParameter("sexo");
            String dataNascimentoStr = request.getParameter("data_nascimento");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date data_nascimento = null;
            try {
                data_nascimento = dateFormat.parse(dataNascimentoStr);
            } catch (ParseException e) {
                e.printStackTrace(); // Lida com a exceção de análise de data inválida
            }
            usuario = daoUsuario.getbyEmail(email);
            
            Cliente cliente = new Cliente(usuario.getId(), email, senha, nome, papel, CPF, telefone, sexo, data_nascimento);
            dao.insert(cliente);
            response.sendRedirect("lista");

        } catch (RuntimeException | IOException e) {
            throw new ServletException(e);
        }
    
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String CPF = request.getParameter("CPF");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String papel = request.getParameter("papel");
            Cliente clienteSelecionado = (Cliente) request.getSession().getAttribute("clienteEdicao");

            if (clienteSelecionado != null) {
                Usuario usuarioSelecionado = daoUsuario.get(clienteSelecionado.getId());
                if (usuarioSelecionado != null) {
                    // Verificar se o email já existe
                    if (daoUsuario.getbyEmail(email) != null
                            && !daoUsuario.getbyEmail(email).getEmail().equals(usuarioSelecionado.getEmail())) {
                        String mensagemErro = "O email já está em uso.";
                        request.setAttribute("mensagemErro", mensagemErro);
                        apresentaFormEdicao(request, response);

                    // Verificar se o CPF já existe
                    if (dao.get(CPF) != null
                            && !dao.get(CPF).getCPF().equals(clienteSelecionado.getCPF())) {
                        mensagemErro = "O CPF já está em uso.";
                        request.setAttribute("mensagemErro", mensagemErro);
                        apresentaFormEdicao(request, response);
                        //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/formulario.jsp");
                        //dispatcher.forward(request, response);
                        //return;
                    }
                }

            }
            
            Usuario usuario = daoUsuario.get(Long.parseLong(request.getParameter("id")));

            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setPapel(papel);
            daoUsuario.update(usuario);

            String telefone = request.getParameter("telefone");
            String sexo = request.getParameter("sexo");

            String dataNascimentoStr = request.getParameter("data_nascimento");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date data_nascimento = null;
            try {
                data_nascimento = dateFormat.parse(dataNascimentoStr);
            } catch (ParseException e) {
                e.printStackTrace(); // Lida com a exceção de análise de data inválida
            }
            
            Cliente cliente = dao.get(usuario.getId());

            cliente.setCPF(cpf);
            cliente.setTelefone(telefone);
            cliente.setSexo(sexo);
            cliente.setDataNascimento(data_nascimento);

            dao.update(cliente);
            response.sendRedirect("lista");
        } catch (RuntimeException | IOException e) {
            throw new ServletException(e);
        }
    }



   private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Usuario usuario = daoUsuario.get(Long.parseLong(request.getParameter("id")));
            daoUsuario.delete(usuario);
            response.sendRedirect("lista");
        } catch (RuntimeException | IOException e) {
            throw new ServletException(e);
        }
    }

}