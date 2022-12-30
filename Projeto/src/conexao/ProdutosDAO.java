package conexao;

import negocio.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    //Criando um método com retorno do tipo Carro, para retornar todos os dados
    public Produto buscaPorId(int id, Produto car) {
        Conexao conexao = new Conexao(); // Definindo o obj de conexão

        PreparedStatement st = null;
        ResultSet rs = null; //Aponta para posição 0 (Zero)

        try {
            String sql = "";
            sql += "";
            sql += "SELECT * from tb_produtos "
                    + "WHERE idprodutos = ?";

            //Definição da query SQL
            st = conexao.getConexao().prepareStatement(sql);

            //Definindo o que foi recuperado da query, neste caso o ID
            st.setInt(1, id);
            rs = st.executeQuery(); //Retorna os dados em modo de TABELA

            if (rs.next()) { //Para testar se veio algum resultado

                car.setIdProdutos(rs.getInt("idProdutos"));
                car.setProduto(rs.getString("produto"));
                car.setValor(rs.getDouble("valor"));
                car.setCusto(rs.getDouble("custo"));
                car.setLucro(rs.getDouble("lucro"));
                car.setVendamedia(rs.getInt("vendamedia"));
                car.setEstoque(rs.getInt("estoque"));
                car.setDataRepor(rs.getString("dataRepor"));
  
                return car;
            }
            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexao.fechaConexao();
        }
        return null;
    }

    // Método para retornar lista de carros, como todos os carros
    public List<Produto> obterListaDeCarros() throws SQLException {
        try {
            //Conecta ao banco de dados por meio da classe de conexão
            Conexao con = new Conexao();
            con.getConexao(); //Obtendo a conexão

            // Comando SQL na base = tabela de carros
            String sql = "select * from tb_produtos;";

            //Executa a query (comando SQL)
            PreparedStatement comando = con.getConexao().prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();

            //Prepara a lista de carros para retornar
            List<Produto> listaDeCarros;
            listaDeCarros = new ArrayList<>();

            //Para cada item retornado no comando (SQL) faça...
            while (resultado.next()) {
                Produto car = new Produto(); //Criando uma instância, novo carro na memória

                car.setIdProdutos(resultado.getInt("idProdutos"));
                car.setProduto(resultado.getString("produto"));
                car.setValor(resultado.getDouble("valor"));
                car.setCusto(resultado.getDouble("custo"));
                car.setLucro(resultado.getDouble("lucro"));
                car.setVendamedia(resultado.getInt("vendamedia"));
                car.setEstoque(resultado.getInt("estoque"));
                car.setDataRepor(resultado.getString("dataRepor")); 
         

                // Insere o carro na lista Local
                listaDeCarros.add(car);
            }

            // Após terminar, fecha a conexão
            resultado.close();
            comando.close();
            con.getConexao().close();

            // Retorna a lista de carros
            return listaDeCarros;
        } catch (SQLException e) { //Caso dê alguma exceção
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void insereCarro(Produto car, int mes, String produto) throws SQLException {
        Conexao conexao = new Conexao();

        PreparedStatement st = null;

        try {

            String sql = "";
            sql += "";
            sql += "INSERT INTO tb_produtos "
                    + "(produto, valor, custo, lucro, vendamedia, estoque) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?);";
                    

            st = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, car.getProduto());
            st.setDouble(2, car.getValor());
            st.setDouble(3, car.getCusto());
            st.setDouble(4, car.getLucro());
            st.setInt(5, car.getVendamedia());
            st.setInt(6, car.getEstoque()); 
            
            /*String sql1 = "";
            sql1 += "";
            sql1 += "update tb_produtos "
                    + "set dataRepor = date_add(date_format(SYSDATE(), '%Y-%m-%d' ), INTERVAL 2 MONTH)"
                    + " where idProdutos = LAST_INSERT_ID();";
            
            st = conexao.getConexao().prepareStatement(sql1);*/
            
            System.out.println("O mês é = " + mes);

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    car.setIdProdutos(id);
                }
                rs.close();
            } else {
                throw new SQLException("Erro inesperado! Nenhuma linha afetada!");
                
            }
            
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } finally {
          
            conexao.fechaConexao();
        }

    }

    //Método que fará a atualização dos dados de um veículo
    
    public void update_data_inserir(String produto, int mes) throws SQLException {
        Conexao conexao = new Conexao();

        PreparedStatement st = null;

        try {
            
            String sql1 = "";
            sql1 += "";
            sql1 += "update tb_produtos " 
                    + "set dataRepor = date_add(date_format(SYSDATE(), '%Y-%m-%d' ), INTERVAL " + mes + " MONTH)"
                    + " where produto = '"
                    + produto
                    + "'";
            
            st = conexao.getConexao().prepareStatement(sql1);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } finally {
          
            conexao.fechaConexao();
        }

    }
    
    public void update_data_atualizar(int id, int mes) throws SQLException {
        Conexao conexao = new Conexao();

        PreparedStatement st = null;

        try {
            
            String sql1 = "";
            sql1 += "";
            sql1 += "update tb_produtos " 
                    + "set dataRepor = date_add(date_format(SYSDATE(), '%Y-%m-%d' ), INTERVAL " + mes + " MONTH)"
                    + " where idProdutos = "
                    + id;
                    
            
            st = conexao.getConexao().prepareStatement(sql1);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } finally {
          
            conexao.fechaConexao();
        }

    }

    
    public void atualizaCarro(Produto car, int id) throws SQLException {
        Conexao conexao = new Conexao();

        PreparedStatement st = null;

        try {
            String sql = "";
            sql += "";
            sql += "UPDATE tb_produtos "
                    + "SET produto = ?, valor = ?, custo = ?, "
                    + "lucro = ?, vendamedia = ?, estoque = ? "
                    + "WHERE idProdutos = ?";

            //Execução do objeto que representa a instrução SQL
            st = conexao.getConexao().prepareStatement(sql);

            //Definindo a parametrização dos objetos a serem alterados no banco
            st.setString(1, car.getProduto());
            st.setDouble(2, car.getValor());
            st.setDouble(3, car.getCusto());
            st.setDouble(4, car.getLucro());
            st.setInt(5, car.getVendamedia());
            st.setInt(6, car.getEstoque());
            st.setInt(7, car.getIdProdutos());

            //Executa a atualização
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            //conexao.fechaConexao();
            st.close();
        }
    }

    public void deletarPorId(Integer id) throws SQLException {

        Conexao conexao = new Conexao();
        PreparedStatement st = null;

        try {
            String sql = "";
            sql += "DELETE FROM tb_produtos "
                + "WHERE idProdutos = ?";
            
            //Execução do objeto que representa a instrução SQL
            st = conexao.getConexao().prepareStatement(sql);
            
            st.setInt(1, id); // Parametrizar o placeholder "?"
            st.executeUpdate(); // 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            st.close();
        }
    }
    
    public void deletarPorProduto(String produto) throws SQLException {

        Conexao conexao = new Conexao();
        PreparedStatement st = null;

        try {
           
            String sql = "";
            sql += "DELETE FROM tb_produtos "
                + "WHERE produto = ?";
            
            //Execução do objeto que representa a instrução SQL
            st = conexao.getConexao().prepareStatement(sql);
            
            st.setString(1, produto); // Parametrizar o placeholder "?"
            st.executeUpdate(); // 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            st.close();
        }
    }

    public void atualizaCarro(Produto carro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
