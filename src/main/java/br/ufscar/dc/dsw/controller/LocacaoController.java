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
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;

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

    private void enviarEmail(String destinatario, String assunto, String mensagem) {
        // Configurações do servidor SMTP do Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Informações da conta de e-mail
        final String emailRemetente = "testdsw1ufscar@gmail.com";
        final String senhaRemetente = "wspgvoulmqwnikyk";

        // Cria uma sessão com as configurações e autenticação
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailRemetente, senhaRemetente);
            }
        });

        try {
            // Cria uma nova mensagem de e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailRemetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem);

            // Envia a mensagem
            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }


    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        LocalDate data = LocalDate.parse(request.getParameter("data"));
        int hora = Integer.parseInt(request.getParameter("hora"));
        LocalDateTime data_hora = LocalDateTime.of(data, LocalTime.of(hora, 0));
        // Verificar se a hora é anterior a hora atual
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
        // Verifica se a locação está disponível
        if (!verifica_disponibilidade(locadora, cliente, data_hora)) {
            Erro erros = new Erro();
            erros.add("Não foi possível cadastrar sua locação: horário indisponível");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
            return;
        }
        dao.insert(locacao);

        String emailCliente = cliente.getEmail();
        String assuntoCliente = "Locação realizada com sucesso";
        String mensagemCliente = "Prezado(a) " + cliente.getNome() + ", a sua locação foi realizada com sucesso.";
        enviarEmail(emailCliente, assuntoCliente, mensagemCliente);
        
        // Envia e-mail para as locadoras
        String emailLocadora = locadora.getEmail();
        String assuntoLocadora = "Nova locação realizada";
        String mensagemLocadora = "Prezado(a) " + locadora.getNome() + ", uma nova locação foi realizada. Favor verificar os detalhes.";
        enviarEmail(emailLocadora, assuntoLocadora, mensagemLocadora);
        
        response.sendRedirect("lista");
    }
    
    public boolean verifica_disponibilidade(Locadora locadora, Cliente cliente, LocalDateTime data_hora) {

        // Verificar se já existe uma locação com o mesmo cliente e horário
        List<Locacao> listaLocacoes = dao.getAll();
        for (Locacao locacao : listaLocacoes) {
            if (locacao.getDataHora().equals(data_hora) && !locacao.getLocadora().equals(locadora)) {
                return false;
            }
        }
        
        // Verificar se já existe uma locação com a mesma locadora e horário
        for (Locacao locacao : listaLocacoes) {
            if (locacao.getDataHora().equals(data_hora) && !locacao.getCliente().equals(cliente)) {
                return false;
            }
        }
        
        // Caso todas as verificações passem, a locação é válida
        return true;
    }
}