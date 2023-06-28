package dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entd.Contrato;
import entd.Construtor;

public class DaoContrato {
	
	public boolean inserir(Contrato contrato) throws SQLException {
		
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "insert into contrato (descricao, orcamento, prioridade, construtor_id) values(? , ? , ?, ?);";
		PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, contrato.getDescricao());
		ps.setInt(2, contrato.getOrcamento());
		ps.setInt(3, contrato.getPrioridade());
        ps.setInt(4, contrato.getCont().getId() );
		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			contrato.setId(id);
		}
		
		return (linhasAfetadas == 1 ? true : false);
	}

	public boolean atualizar(Contrato cont) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "update contrato set descricao = ?, orcamento = ? ,  prioridade = ? where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cont.getDescricao());
		ps.setInt(2, cont.getOrcamento());
		ps.setInt(3, cont.getPrioridade());
		ps.setInt(4, cont.getId());
		
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean excluir(int id) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "delete from contrato where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public Contrato buscar(int idBuscado) throws SQLException {
		
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from contrato where id = ?";
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Contrato cont = null;
		
		if( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int orcamento = result.getInt("orcamento");
			int prioridade = result.getInt("prioridade");
			int idUsuario = result.getInt("construtor_id");
			
			
			
			DaoConstrutor daoUser = new DaoConstrutor();
			Construtor u = daoUser.buscarPorId(idUsuario);
			
			cont = new Contrato(id, descricao, orcamento,prioridade, u );
		}
		
		return cont;
	}
	

	public List<Contrato> buscarTodas() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from contrato";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Contrato> cont = new ArrayList<Contrato>();
		
		DaoConstrutor daoUser = new DaoConstrutor();

		while( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int orcamento = result.getInt("orcamento");
			int prioridade = result.getInt("prioridade");
			int idUsuario = result.getInt("construtor_id");
			
			
		
			
			
			Construtor u = daoUser.buscarPorId(idUsuario);
			
			Contrato t = new Contrato(id, descricao, orcamento, prioridade,u );
	
			cont.add(t);
		}
		
		return cont;
	}

	public List<Contrato> pesquisarPorDescricao(String texto) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from contrato where descricao like ? ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "%"+texto+"%");
		
		ResultSet result = ps.executeQuery();
		
		List<Contrato> contrato = new ArrayList<Contrato>();
		
		DaoConstrutor daoUser = new DaoConstrutor();
		
		while( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int orcamento = result.getInt("orcamento");
			int prioridade = result.getInt("prioridade");
			int idUsuario = result.getInt("construtor_id");
		    
			
			Construtor u = daoUser.buscarPorId(idUsuario);
			Contrato t = new Contrato(id, descricao,orcamento, prioridade, u );
	
			contrato.add(t);
		}
		
		return contrato;
	}
	
	public List<Contrato> tarefasPorUsuario(String email) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from contrato left join construtor on contrato.construtor_id = construtor.id where construtor.email = ?;";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, email);
		
		ResultSet result = ps.executeQuery();
		
		List<Contrato> contrato = new ArrayList<Contrato>();
		
		
		if( result.next() ) {			
			int idUser = result.getInt("construtor_id");
			String senha = result.getString("senha");
			
			Construtor construtor = new Construtor(idUser, email, senha);
			
			do {
				int id = result.getInt("id");
				String descricao = result.getString("descricao");
				int orcamento = result.getInt("orcamento");
				int prioridade = result.getInt("prioridade");
				
				
				Contrato t = new Contrato(id, descricao, orcamento, prioridade,construtor );
		
				contrato.add(t);
			}while(result.next());
		}
		
		return contrato;
	}

}
