package database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class DatabaseAzure {

    private BasicDataSource dataSource;
    private JdbcTemplate template;

    public DatabaseAzure() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://servidor-pycem.database.windows.net:1433;databaseName=pycem-db");
        dataSource.setUsername("adminpy");
        dataSource.setPassword("#Gfgrupo8");
        template = new JdbcTemplate(dataSource);
    }
    
    public Integer selectIdTotem(String usuario) {
        Totem totemListado = template.queryForObject("SELECT idTotem FROM [dbo].[totem] WHERE usuario = ?;",
                new BeanPropertyRowMapper<Totem>(Totem.class), usuario);
        return totemListado.getIdTotem();
    }
   
    
    public Alerta selectAlerta(String usuario) {
        Alerta alerta = template.queryForObject(
                "SELECT * FROM [dbo].[alerta] WHERE fkEmpresa = (select unidade.fkEmpresa from totem " +
                "join unidade on unidade.idUnidade = totem.fkUnidade " +
                "join empresa on unidade.fkEmpresa = empresa.idEmpresa where usuario = ?);",
                new BeanPropertyRowMapper<Alerta>(Alerta.class), usuario);
        return alerta;
    }
    
    // Trocar o login pelo da máquina
    public Boolean selectLogin(String usuario, String senha) {
        Totem totemListado = template.queryForObject("SELECT usuario, senha FROM [dbo].[totem] WHERE usuario = ? AND senha = ?;",
                new BeanPropertyRowMapper<Totem>(Totem.class), usuario, senha);
        
        if (totemListado.getUsuario().equals(usuario)){
            return true;
        } else {
            return false;
        }
    }
    // Verificar o cadastro da máquina
    public Boolean verificarCadastro(String usuario){
        Totem totemListado = template.queryForObject("SELECT processador FROM totem WHERE usuario = ?",
                new BeanPropertyRowMapper<Totem>(Totem.class), usuario);
        
        String teste = String.format("%s", totemListado.getProcessador());
        if (totemListado.getProcessador().equals("Não Especificado")){
            return false;
        } else {
            return true;
        }        
    } 
    
    public void atualizarCadastro(
            String usuario,
            String numeroSerie,
            String processador,
            String ram,
            String tipoArmazenamento,
            String qtdArmazenamento,
            String ipv6,
            String macAdress){
        template.update(
                "UPDATE [dbo].[totem] SET numeroSerie = ?, processador = ?, ram = ?, tipo_armazenamento = ?, qtd_armazenamento = ?, "
                        + "ipv6 = ?, mac_address = ? WHERE usuario = ?;", 
                numeroSerie, 
                processador, 
                ram, 
                tipoArmazenamento, 
                qtdArmazenamento, 
                ipv6, 
                macAdress,
                usuario);
    
    }
    
    public void inserirDados(
            String usoProcessador,
            String usoRam,
            String usoHd,
            String cpuStatus,
            String ramStatus,
            String hdStatus,
            Integer fkTotem
    ){
        template.update("INSERT INTO [dbo].[registro](uso_processador, uso_ram, uso_hd, cpu_status, ram_status, hd_status, data_registro, fkTotem) "
                + "VALUES (?, ?, ?, ?, ?, ?, getDate(), ?);",
                usoProcessador,
                usoRam,
                usoHd,
                cpuStatus,
                ramStatus,
                hdStatus,
                fkTotem);
    
    }
    
    public void ligarMaquina(String usuario, Integer idTotem){
        template.update(
                "UPDATE totem SET estado = 'Disponivel' where usuario = ?", usuario);
        template.update(
                "INSERT INTO historico_totem (estadoTotem, data_historico, fkTotem) VALUES('Disponivel', getDate(), ?) ", idTotem);
    }
    
    public void desligarMaquina(String usuario, Integer idTotem){
        template.update(
                "UPDATE totem SET estado = 'Desligado' WHERE usuario = ?", usuario);
        template.update(
                "INSERT INTO historico_totem (estadoTotem, data_historico, fkTotem) VALUES('Desligado', getDate(), ?) ", idTotem);
    }
}