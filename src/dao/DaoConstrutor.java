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

public class DaoConstrutor {
	
public boolean inserir(Construtor construtor) throws SQLException {
		
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "insert into construtor (email, senha) values(? , sha2( ? , 256 ));";
		PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, construtor.getEmail());
		ps.setString(2, construtor.getSenha());

		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			construtor.setId(id);
		}
		
		return (linhasAfetadas == 1 ? true : false);
	}
	
	public boolean atualizarDados(Construtor construtor) throws SQLException {
		return true;
	}
	
	public boolean atualizarSenha(Construtor construtor) throws SQLException {
		return true;
	}

	public boolean excluir(int id) throws SQLException {
		return true;
	}
	
	public Construtor buscarPorId(int idBuscado) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from construtor where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Construtor construtor = null;
		
		if( result.next() ) {
			int id = result.getInt("id");
			String email = result.getString("email");
			String senha = result.getString("senha");
			
			construtor = new Construtor(id, email, senha);
		}
		
		return construtor;
	}
	
	public Construtor buscarPorEmail(String email) throws SQLException {
		return null;
	}
	
	public List<Construtor> buscarTodos() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from construtor";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Construtor> construtors = new ArrayList<Construtor>();
		
		while( result.next() ) {
			int id = result.getInt("id");
			String email = result.getString("email");
			String senha = result.getString("senha");
			
			Construtor construtor = new Construtor(id, email, senha);
	
			construtors.add(construtor);
		}
		
		return construtors;
	}

}
