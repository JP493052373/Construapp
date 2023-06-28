package construapp;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DaoContrato;
import dao.DaoConstrutor;
import entd.Contrato;
import entd.Construtor;

public class Programa {

	private static DaoContrato daoContrato = new DaoContrato();
	private static DaoConstrutor daoCont = new DaoConstrutor();
	private static boolean t;
	private static boolean a;
	private static boolean c;
	
	public static void main(String[] args) throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		int opc ;
	    
		
		do {
			  
			
			System.out.println(" Bem vindo ao ConstruApp ");
			System.out.println("Escolha uma Opcao 7  de cadastro  abaixo para comecamos:");
			System.out.println("1 - Cadastrar Contrato");
			System.out.println("2 - Atualizar Contrato");
			System.out.println("3 - Buscar Contrato");
			System.out.println("4 - Excluir Contrato");
			System.out.println("5 - Listar Contrato");
			System.out.println("6 - Pesquisar Contrato");
			System.out.println("7 - Cadastrar Construtor");
			System.out.println("8 - Listar Construtor");
			System.out.println("9 - Listar os Contrato por Construtor");
			
			System.out.println("0 - Sair");
			
			
			do {
				try {
					opc = Integer.parseInt( scanner.nextLine()  );
				} catch(Exception e){
					System.out.println("Digite algo valido ");
					opc = -1 ;
				}
			}while(opc == -1);
			
			
			

		
			
			switch(opc) {
			
				case 1:
					cadastrarContrato();
					break;
				case 2:
					atualizarContrato();
					break;
				case 3:
					buscarContrato();
					break;
				case 4:
					excluirContrato();
					break;
				case 5:
					listarContrato();
					break;
				case 6:
					pesquisarContrato();
					break;
				case 7:
					cadastrarConstrutor();
					break;
				case 8:
					listarConstrutor();
					break;
				case 9:
					listarContratoConstrutor();
					break;
				case 0:
					System.out.println("Adeus.");
					break;
				default:
					System.out.println("Opcao invalida!");
					
			}  
		
		
			
		}while(opc != 0);
	
		
		
		
		
	}
	
	public static void cadastrarContrato() throws SQLException {
		System.out.println("------Cadastros de  Contrato------");
		Scanner scanner = new Scanner(System.in);
		
		

		System.out.println("Digite a  descricao do contrato: ");
		String descricao = scanner.nextLine();
		
		System.out.println("Digite o orcamento do contrato: ");
		int orcamento =  Integer.parseInt(scanner.nextLine());
		
		System.out.println("Informe a prioridade do Contrato: ");
		int prioridade = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Informe o ID do Construtor: ");
		int idUser = Integer.parseInt(scanner.nextLine());

		Construtor u = daoCont.buscarPorId(idUser);
		
		if(u != null) {
			Contrato t = new Contrato( descricao, orcamento, prioridade,u );
	
			System.out.println( daoContrato.inserir( t ) ? "Cadastrou!" : "Falha do cadastro...");
	
			System.out.println("Contrato cadastrado sob o ID " + t.getId());
		}else {
			System.out.println("Nao existe usuario com o ID informado!");
		}
		
 }
	
	public static void atualizarContrato() throws SQLException{
		System.out.println("------Atualizando Contrato------");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		Contrato cont = daoContrato.buscar(id);
		
		if(cont != null) {
			
			System.out.println(" Descricao do Contrato : " + cont.getDescricao());
			System.out.println("Informe a novo Contrato  ou pressione enter:");
			
			
			String desc = scanner.nextLine();
			
			 if (!desc.isEmpty()) {
		            cont.setDescricao(desc);
		        }
			
			
			
			System.out.println("orcamento atual do contrato : " + cont.getOrcamento());
			System.out.println("Informe a novo orcamento do  Contrato  ou pressione enter:");
			
			
			String orcamento = scanner.nextLine();
			
			 if (!orcamento.isEmpty()) {
		            cont.setOrcamento(Integer.parseInt(orcamento));
		        }
			
			
			
			System.out.println("Prioridade atual do Contrato: " + cont.getPrioridade());
			System.out.println("Digite  a nova prioridade ou pressione enter:");
			
			String prioridade = scanner.nextLine();
			
			if (!prioridade.isEmpty()) {
	            cont.setPrioridade(Integer.parseInt(prioridade));
	        }
			
			
			
			if( daoContrato.atualizar(cont) ) {
				System.out.println("Contrato atualizado com sucesso!");
			}else {
				System.out.println("Ao que parece aconteceu um  erro ao atualizar. tente novamente");
			}
			
		}else {
			System.out.println("Erro ao localizar contrato. O contrato  "+ id +" existe?");
		}
	}
	
	public static void buscarContrato() throws SQLException {
		System.out.println("\n-------Buscando Contrato por ID-------");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		Contrato t = daoContrato.buscar(id);
		
		if(t != null) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descricao do contrato: " + t.getDescricao());
			System.out.println("Orcamento: "+"R$:" + t.getOrcamento());
			System.out.println("Prioridade: " + t.getPrioridade());
			
			System.out.println("Construtor: " + t.getCont().getEmail() +"\n");
		}else {
			System.out.println("Contrato nao existe...");
		}
         boolean r = daoContrato.excluir(id);
	}
		
		
	
	
	

	public static void excluirContrato() throws SQLException{
		System.out.println("\n-------Excluindo Contrato por ID----------");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		boolean r = daoContrato.excluir(id);
		
		if( r ) {
			System.out.println("Contrato excluido!");
		}else {
			System.out.println("Ao que parece aconteceu algum  erro ao excluir o Contrato . A Contrato "+ id +" existe?");
		}
	}
	
	public static void listarContrato() throws SQLException {
		
		System.out.println("\n-------Listar Contrato-----------\n");
		
		List<Contrato> tasks = daoContrato.buscarTodas();

		Scanner scanner = new Scanner(System.in);
		
		for(Contrato t : tasks) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descricao do contrato: " + t.getDescricao());
			System.out.println("Orcamento: "+"R$:" + t.getOrcamento());
			System.out.println("Prioridade: " + t.getPrioridade());
			
			System.out.println("Construtor: " + t.getCont().getEmail() +"\n");
 
			
			scanner.nextLine();
		}
		
		
		if( t ) {
		}else {
			System.out.println("Ao que parece aconteceu algum  erro na Lista de Contrato.Por favor tente novamente ");
		}
	}

	public static void pesquisarContrato() throws SQLException {
		System.out.println("\n -----Buscando Contrato  por Descricao -----");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe a descricao do Contrato: ");
		String pesquisa = scanner.nextLine();

		List<Contrato> tasks = daoContrato.pesquisarPorDescricao(pesquisa);
		
		for(Contrato t : tasks) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descricao do contrato: " + t.getDescricao());
			System.out.println("Orcamento: "+"R$:" + t.getOrcamento());
			System.out.println("Prioridade: " + t.getPrioridade());
			System.out.println("Construtor: " + t.getCont().getEmail() +"\n");
			
			scanner.nextLine();
		}
		if( c) {
		}else {
			System.out.println("Ao que parece aconteceu algum  erro na Busca de Contrato.Por favor tente novamente ");
		}
	}

	public static void cadastrarConstrutor() throws SQLException{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite seu email: ");
		String email = scanner.nextLine();
		
		System.out.println("Informe sua senha: ");
		String senha = scanner.nextLine();

		Construtor construtor = new Construtor(email, senha);

		System.out.println( daoCont.inserir( construtor ) ? "Cadastrou!" : "Falha do cadastro de Construtor...");

		System.out.println("Construtor cadastrado sob o ID " + construtor.getId());
		
		
	}
	
	public static void listarConstrutor() throws SQLException {
		
		System.out.println("\n-------Listar Construtores--------\n");
		
		List<Construtor> users = daoCont.buscarTodos();
		
		for(Construtor u : users) {
			System.out.println("ID: " + u.getId());
			System.out.println("E-mail: " + u.getEmail());
			System.out.println("Senha: " + u.getSenha());
		}
	}
	
	public static void listarContratoConstrutor() throws SQLException{
		System.out.println("\n -------Listar de Contrato por Construtor--------\n");
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o email do Construtor: ");
		String email = scanner.nextLine();
		
		List<Contrato> tasks = daoContrato.tarefasPorUsuario(email);
		
		for(Contrato t : tasks) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descricao do contrato: " + t.getDescricao());
			System.out.println("Orcamento: "+"R$:" + t.getOrcamento());
			System.out.println("Prioridade: " + t.getPrioridade());
			System.out.println("Construtor: " + t.getCont().getEmail() +"\n");
			
			scanner.nextLine();
		}
		if( a ) {
		}else {
			System.out.println(" \n Ao que parece ocorreu um erro na Lista de Contrato por Construtor.Por favor tente novamente \n ");
		}
		
	}
	
	
	
}


		
		
		

