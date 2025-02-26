package Admin;

import Nomina.Prenomina.*;

import Nomina.Listas.*;
import Semanal.PT_4;
import Semanal.Tehuantepec_4;
import Semanal.Iturbide_4;
import Nomina.ModulosS.CDAS_5;
import Nomina.ModulosS.ODTS_5;
import Nomina.ModulosS.PresS_5;
import Nomina.ModulosQ.CDAQ_5;
import Nomina.ModulosQ.ODTQ_5;
import Nomina.ModulosQ.PresQ_5;
import RH.Depositos.Santander.*;
import Nomina.*;
import ColoresT.*;
import Conexion.ConexionSQL;
import Filtros.FiltrosZonas;
import Inicio.Inicio_1;
import Logicas.*;
import Pensiones.PensionesVPIturbide_7;
import RH.*;
import RH.Depositos.DepositosQ_4;
import RH.Depositos.DepositosQ_SIMSS_4;
import RH.Depositos.DepositosS_4;
import RH.Depositos.DepositosS_SIMSS_4;
import Semanal.Padrones.Padrones;
import Semanal.Vales.Rvales;
import Semanal.Vales.VDE;
import java.sql.ResultSetMetaData;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JDeat
 */
public final class AltasZyS_3 extends javax.swing.JFrame {

    ConexionSQL cc = new ConexionSQL();
    Connection con = cc.conexion();
    ButtonGroup Fil;
    ColorZyS colores = new ColorZyS();
    ColorNSD colorNSD = new ColorNSD();
    ColorSDS colorSDS = new ColorSDS();
    Logica_usuarios usr;
    Logica_permisos LP;

    public AltasZyS_3() {
        initComponents();
        MostrarServSem();
        this.setExtendedState(6);
        this.setLocationRelativeTo(null);
        IDS.setVisible(false);
        IDZ.setVisible(false);
        idZona.setVisible(false);
        NZS.setVisible(false);
        Filtros.setVisible(false);
        SabadoT.setEnabled(false);
        DomingoT.setEnabled(false);
        Otrotxt.setEnabled(false);
        Costo.setVisible(false);
        Fil = new ButtonGroup();
        Fil.add(FPNDS);
        Fil.add(FPNDZ);
        Costo.setVisible(false);
        //El combobox jala datos de la base de datos
        FiltrosZonas zz = new FiltrosZonas();
        DefaultComboBoxModel modelzonas = new DefaultComboBoxModel(zz.mostrarzonas());
        FiltroZ.setModel(modelzonas);
        mostrarzonas();
        mostrarServicios();
        compartirZ();
        ContarServ();
        nds();
        SDS();
        TNDS.setDefaultRenderer(TNDS.getColumnClass(0), colorNSD);
        TServ.setDefaultRenderer(TServ.getColumnClass(0), colores);
        TStatusServ.setDefaultRenderer(TStatusServ.getColumnClass(0), colorSDS);
        setIconImage(new ImageIcon(AltasZyS_3.class.getClassLoader().getResource("Imagenes/Icono.png")).getImage());
    }

    public AltasZyS_3(Logica_usuarios usr, Logica_permisos LP) {
        initComponents();
        MostrarServSem();
        this.usr = usr;
        this.LP = LP;
        this.setExtendedState(6);
        this.setLocationRelativeTo(null);
        IDS.setVisible(false);
        IDZ.setVisible(false);
        idZona.setVisible(false);
        NZS.setVisible(false);
        Filtros.setVisible(false);
        SabadoT.setEnabled(false);
        DomingoT.setEnabled(false);
        Otrotxt.setEnabled(false);
        Costo.setVisible(false);
        Fil = new ButtonGroup();
        Fil.add(FPNDS);
        Fil.add(FPNDZ);
        Costo.setVisible(false);
        //El combobox jala datos de la base de datos
        FiltrosZonas zz = new FiltrosZonas();
        DefaultComboBoxModel modelzonas = new DefaultComboBoxModel(zz.mostrarzonas());
        FiltroZ.setModel(modelzonas);
        mostrarzonas();
        mostrarServicios();
        compartirZ();
        ContarServ();
        nds();
        SDS();
        TNDS.setDefaultRenderer(TNDS.getColumnClass(0), colorNSD);
        TServ.setDefaultRenderer(TServ.getColumnClass(0), colores);
        TStatusServ.setDefaultRenderer(TStatusServ.getColumnClass(0), colorSDS);
        setIconImage(new ImageIcon(AltasZyS_3.class.getClassLoader().getResource("Imagenes/Icono.png")).getImage());
        setTitle("Zonas y servicios # Usuario: " + usr.getId_user() + " " + usr.getApellidop() + " " + usr.getApellidoM() + " " + usr.getNombre()
                + " Tipo de ususario: " + usr.getNombre_tipo() + " Usuario: " + usr.getUsuario());
    }

    @SuppressWarnings("unchecked")

    public void ModsServSem() {

        String SQL = "UPDATE `servicios." + Semcbx.getSelectedItem().toString() + "`  SET `#Serv` = ?, `Servicio` = ? WHERE `servicios." + Semcbx.getSelectedItem().toString() + "`.`#Serv` = ?";

        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setInt(1, Integer.parseInt(NServSem.getText()));
            pst.setString(2, SAR.getText());
            pst.setInt(3, Integer.parseInt(NServSem.getText()));

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio semanal en " + Semcbx.getSelectedItem().toString() + " modificado.");
            MostrarServSem();

            Semcbx.setSelectedIndex(0);
            NServSem.setText("0");
            SAR.setText("");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar servicio semanal en " + Semcbx.getSelectedItem().toString() + " : \n" + e.getMessage());
        }
    }

    public void AgregarServSem() {

        String SQL = "INSERT INTO `servicios." + Semcbx.getSelectedItem().toString() + "` (`#Serv`, `Servicio`) VALUES (?, ?)";

        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setInt(1, Integer.parseInt(NServSem.getText()));
            pst.setString(2, SAR.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio semanal en " + Semcbx.getSelectedItem().toString() + " agregado.");
            MostrarServSem();

            Semcbx.setSelectedIndex(0);
            NServSem.setText("0");
            SAR.setText("");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar servicio semanal en " + Semcbx.getSelectedItem().toString() + " : \n" + e.getMessage());
        }
    }

    public void MostrarServSem() {

        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };
//nombre tabla
            TServInt.setModel(modelo);

            PreparedStatement ps;
            ResultSet rs;

            String sql = "SELECT * FROM `servicios.iturbide`";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("# Servicio");
            modelo.addColumn("Nombre de Servicio");

//Anchos
            int[] anchos = {30, 200};

            for (int x = 0; x < cantidadColumnas; x++) {
                //nombre tabla
                TServInt.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos de servicios de iturbide: \n" + e.getMessage());

        }

        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };
//nombre tabla
            TServPte.setModel(modelo);

            PreparedStatement ps;
            ResultSet rs;

            String sql = "SELECT * FROM `servicios.puente titla`";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("# Servicio");
            modelo.addColumn("Nombre de Servicio");

//Anchos
            int[] anchos = {30, 200};

            for (int x = 0; x < cantidadColumnas; x++) {
                //nombre tabla
                TServPte.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos de servicios de puente titla: \n" + e.getMessage());

        }

        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };
//nombre tabla
            TServTehua.setModel(modelo);

            PreparedStatement ps;
            ResultSet rs;

            String sql = "SELECT * FROM `servicios.tehuantepec`";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("# Servicio");
            modelo.addColumn("Nombre de Servicio");

//Anchos
            int[] anchos = {30, 200};

            for (int x = 0; x < cantidadColumnas; x++) {
                //nombre tabla
                TServTehua.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos de servicios de Tehuantepec: \n" + e.getMessage());

        }
    }

//Contar filas
    public void ContarServ() {
        int filas = TServ.getRowCount();
        TdServ.setText("Total de servicios: " + filas);
    }

//Modificar Servicio
    public void modS() {
        String Costov = "";
        String Q = (String) Cbxcosto.getSelectedItem();
        if (Q.equals(",")) {
            Costov = "";
        }
        if (Q.equals("Con costo")) {
            Costov = Costo.getText();

        }
        if (Q.equals("Sin costo")) {
            Costov = "Sin costo";
        }
        if (Q.equals(".")) {
            Costov = "";
        }
        String horarioSMOD;
        if (Sab.isSelected() == true) {
            //Entre Semana Sabado
            horarioSMOD = SabadoT.getText();

        } else {
            boolean name = Sab.isSelected() == false;
            horarioSMOD = "OFF";
        }
        String horarioDMOD;
        if (Dom.isSelected() == true) {
            //Entre semana Domingo
            horarioDMOD = DomingoT.getText();
        } else {
            boolean name = Dom.isSelected() == false;
            horarioDMOD = "OFF";
        }
        //Todo el dia modificacion
        String TEDMOD;
        if (Allday.isSelected() == true) {
            horarioDMOD = "";
            horarioSMOD = "";
            TEDMOD = "24 HRS";

        } else {
            boolean name = Allday.isSelected() == false;
            TEDMOD = "";
        }
        String OtroMOD;
        if (Otro.isSelected() == true) {
            OtroMOD = Otrotxt.getText();
            horarioDMOD = "";
            horarioSMOD = "";

        } else {
            boolean name = Otro.isSelected() == false;
            OtroMOD = "";

        }
        String TDs = TDS.getSelectedItem().toString();

        int id = Integer.parseInt(IDS.getText());
        String SQL = "UPDATE `servicio` SET `idZona` = ?, `Nombre Zona` = ?, `Supervisor` = ?, `Servicio` = ?,"
                + " `Horario` = ?, `Abre` = ?, `Cierra` = ?,"
                + " `Sabado` = ?, `Domingo` = ?, `Otro` = ?, `Tipo de valet` = ?, `Costo` = ?, `Status` = ? WHERE"
                + " `servicio`.`idServ` = ?";

        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, idZona.getText());
            pst.setString(2, NZS.getText());
            pst.setString(3, Supervisor.getText());
            pst.setString(4, NS.getText());
            pst.setString(5, Horario.getText() + TEDMOD);
            pst.setString(6, Abre.getText());
            pst.setString(7, Cierra.getText());
            pst.setString(8, horarioSMOD);
            pst.setString(9, horarioDMOD);
            pst.setString(10, OtroMOD);
            pst.setString(11, TDs);
            pst.setString(12, Costov);
            pst.setString(13, StatusServ.getSelectedItem().toString());
            pst.setInt(14, id);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio Modificado");
            mostrarServicios();
            ContarServ();
            nds();
            SDS();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage());
        }
    }

//Modificar Zona
    public void modZ() {
        int id = Integer.parseInt(IDZ.getText());
        String SQL = "UPDATE zona SET `Zonas` = ? WHERE (`idZona` = ?)";

        try {
            PreparedStatement pst = con.prepareStatement(SQL);

            pst.setString(1, NZ.getText());
            pst.setInt(2, id);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Zona Modificada.");
            mostrarzonas();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage());
        }
    }

//Eliminar Servicio
    public void eliminarS() {

        try {

            int filaseleccionada = TServ.getSelectedRow();
            String sql = "delete from servicio where idServ=" + TServ.getValueAt(filaseleccionada, 0);
            java.sql.Statement st = con.createStatement();
            int n = st.executeUpdate(sql);
            if (n >= 0) {
                JOptionPane.showMessageDialog(null, "El servicio a sido eliminado.");
                mostrarServicios();
                clearServ();
            }
        } catch (HeadlessException | SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar Servicio: " + e.getMessage());

        }

    }

//Eliminar Zona
    public void eliminarZ() {

        try {

            int filaseleccionada = TablaZona.getSelectedRow();
            String sql = "delete from zona where idZona=" + TablaZona.getValueAt(filaseleccionada, 0);
            java.sql.Statement st = con.createStatement();
            int n = st.executeUpdate(sql);
            if (n >= 0) {
                JOptionPane.showMessageDialog(null, "La Zona a sido eliminada.");
                mostrarzonas();
                compartirZ();
                clearzone();
            }
        } catch (HeadlessException | SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar Zona: " + e.getMessage());

        }

    }
//Agregar Zona

    public void AgregarZ() {

        String SQL = "INSERT INTO `zona` (`Zonas`) VALUES (?)";

        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, NZ.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Zona agregada.");
            mostrarzonas();
            compartirZ();
            clearzone();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + e.getMessage());
        }
    }

//Agregar Servicio
    public void AgregarS() {
        String Costov = "";
        String Q = (String) Cbxcosto.getSelectedItem();
        if (Q.equals(",")) {
            Costov = "";
        }
        if (Q.equals("Con costo")) {
            Costov = Costo.getText();
        }
        if (Q.equals("Sin costo")) {
            Costov = "Sin costo";
        }
        if (Q.equals(".")) {
            Costov = "";
        }
        String horarioS;
        if (Sab.isSelected() == true) {
            //Entre Semana Sabado
            horarioS = SabadoT.getText();

        } else {
            boolean name = Sab.isSelected() == false;
            horarioS = "OFF";
        }
        String horarioD;
        if (Dom.isSelected() == true) {
            //Entre semana Domingo
            horarioD = DomingoT.getText();
        } else {
            boolean name = Dom.isSelected() == false;
            horarioD = "OFF";
        }
        //otro semana
        String OtroS;
        if (Otro.isSelected() == true) {
            OtroS = Otrotxt.getText();
            horarioD = "";
            horarioS = "";
        } else {
            boolean name = Otro.isSelected() == false;
            OtroS = "";

        }
        //Todo el dia
        String TED;
        if (Allday.isSelected() == true) {

            TED = "24 HRS";
            horarioD = "";
            horarioS = "";
        } else {
            boolean name = Allday.isSelected() == false;
            TED = "";
        }
        String TDs = TDS.getSelectedItem().toString();

        String SQL = "INSERT INTO `servicio` (`idZona`, `Nombre Zona`,"
                + " `Supervisor`, `Servicio`, `Horario`, `Abre`, `Cierra`, `Sabado`,"
                + " `Domingo`, `Otro`, `Tipo de valet`, `Costo`, `Status`) VALUES (?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, idZona.getText());
            //Nombre de Zona para Servicio
            pst.setString(2, NZS.getText());
            pst.setString(3, Supervisor.getText());
            //Nombre Servicio
            pst.setString(4, NS.getText());
            pst.setString(5, Horario.getText() + TED);
            pst.setString(6, Abre.getText());
            pst.setString(7, Cierra.getText());
            pst.setString(8, horarioS);
            pst.setString(9, horarioD);
            pst.setString(10, OtroS);
            pst.setString(11, TDs);
            pst.setString(12, Costov);
            pst.setString(13, StatusServ.getSelectedItem().toString());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio agregado.");
            mostrarServicios();
            clearServ();
            nds();
            SDS();
            ContarServ();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + e.getMessage());
        }

    }

    private void clearServ() {
        NS.setText("");
        IDS.setText("");
        idZona.setText("");
        NZS.setText("");
        Supervisor.setText("");
        Horario.setText("L a V");
        TDS.setSelectedIndex(0);
        SabadoT.setText("");
        DomingoT.setText("");
        Otrotxt.setText("");
        Abre.setText("08:30");
        Cierra.setText("16:00");
        Sab.setSelected(false);
        Dom.setSelected(false);
        Allday.setSelected(false);
        Otro.setSelected(false);
        Horario.setEnabled(true);
        Abre.setEnabled(true);
        Cierra.setEnabled(true);
        SabadoT.setEnabled(false);
        DomingoT.setEnabled(false);
        Otrotxt.setEnabled(false);
        Cbxcosto.setSelectedIndex(0);
        Costo.setText("");
        Costo.setVisible(false);
    }

    private void clearzone() {
        NZ.setText("");
        IDZ.setText("");
    }

    //Mostrar Status de servicios
    private void SDS() {
        try {
            //Cargar datos
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };
//Nombre de la tabla
            TStatusServ.setModel(modelo);
            PreparedStatement ps;
            ResultSet rs;

            String sql = "select `Status`, COUNT(`Status`) as sts FROM `servicio`"
                    + " GROUP BY 1 HAVING COUNT(`Status`) > 1;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Status de Servicio");
            modelo.addColumn("#");

//Anchos
            int[] anchos = {210, 10};

            for (int x = 0; x < cantidadColumnas; x++) {
                //Nombre tabla
                TStatusServ.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);

            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos: " + e.getMessage());

        }

    }

    //Mostrar numero de servicios
    private void nds() {
        try {
            //Cargar datos
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };
//Nombre de la tabla
            TNDS.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "select `Tipo de valet`, COUNT(`Tipo de valet`)"
                    + " as xd FROM `servicio` GROUP BY 1 HAVING COUNT(`Tipo de valet`) > 1;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Tipo de Servicio");
            modelo.addColumn("#");

//Anchos
            int[] anchos = {210, 10};

            for (int x = 0; x < cantidadColumnas; x++) {
                //Nombre tabla
                TNDS.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);

            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos " + e.getMessage());

        }

    }

//Mostrar Servicios
    private void mostrarServicios() {
        //Buscar servicio
        String NombreS = BS.getText();
        String where = "";
        //Filtro Zona
        String Filtro2 = FiltroZ.getSelectedItem().toString();
        //Filtros
        if (!"".equals(NombreS)) {
            where = " WHERE `Servicio` LIKE '%" + NombreS + "%'";
        } else if (!"".equals(Filtro2)) {
            where = " Where `Nombre Zona` LIKE '%" + Filtro2 + "%'";
        }
        try {
            //Cargar datos
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };
//Nombre de la tabla
            TServ.setModel(modelo);
            PreparedStatement ps;
            ResultSet rs;

            String sql = "select * from servicio" + where;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Num. servicio");//1
            modelo.addColumn("Num. Zona");
            modelo.addColumn("Zona");//3
            modelo.addColumn("Supervisor");
            modelo.addColumn("Nombre Servicio");//5
            modelo.addColumn("Horario");
            modelo.addColumn("Abre");//7
            modelo.addColumn("Cierra");
            modelo.addColumn("Sabado");//9
            modelo.addColumn("Domingo");
            modelo.addColumn("Otro horario");//11
            modelo.addColumn("Tipo de Servicio");
            modelo.addColumn("Costo");//13
            modelo.addColumn("Status");

//Anchos
            int[] anchos = {10, 10, 50, 150, 150, 30, 20, 20, 30, 30, 225,
                75, 50, 50};

            for (int x = 0; x < cantidadColumnas; x++) {
                //Nombre tabla
                TServ.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);

            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos " + e.getMessage());

        }

    }

    public void mostrarzonas() {
        //Buscar Zonas
        String NombreZ = BZ.getText();
        String where = "";
        if (!"".equals(NombreZ)) {
            where = " WHERE `Zonas` LIKE '%" + NombreZ + "%'";

        }

        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };
//nombre tabla
            TablaZona.setModel(modelo);

            PreparedStatement ps;
            ResultSet rs;

            String sql = "select * from zona" + where;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID de Zona");
            modelo.addColumn("Nombre de Zona");

//Anchos
            int[] anchos = {30, 200};

            for (int x = 0; x < cantidadColumnas; x++) {
                //nombre tabla
                TablaZona.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos " + e.getMessage());

        }
    }

    public void compartirZ() {
        //Buscar Zonas
        String NombreZ = BZS.getText();
        String where = "";
        if (!"".equals(NombreZ)) {
            where = " WHERE `Zonas` LIKE '%" + NombreZ + "%'";

        }

        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                public boolean isCellEditable(int filas, int columna) {
                    return false;
                }
            };

            ZSh.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "select * from zona" + where;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID de Zona");
            modelo.addColumn("Nombre de Zona");

//Anchos
            int[] anchos = {30, 200};

            for (int x = 0; x < cantidadColumnas; x++) {
                ZSh.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos " + e.getMessage());

        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        AgregarZ = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaZona = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        BZ = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        NZ = new javax.swing.JTextArea();
        IDZ = new javax.swing.JTextField();
        EliminarZ = new javax.swing.JButton();
        ModZ = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ZSh = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        IDS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BZS = new javax.swing.JTextField();
        AgrergarS = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        NS = new javax.swing.JTextArea();
        ModS = new javax.swing.JButton();
        idZona = new javax.swing.JTextField();
        NZS = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        Otrotxt = new javax.swing.JTextField();
        HorarioSYD = new javax.swing.JLabel();
        Sab = new javax.swing.JRadioButton();
        Horario = new javax.swing.JTextField();
        Dom = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        Abre = new javax.swing.JTextField();
        Cierra = new javax.swing.JTextField();
        Allday = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        Otro = new javax.swing.JRadioButton();
        Supervisor = new javax.swing.JTextField();
        SabadoT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        DomingoT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Costo = new javax.swing.JTextField();
        Cbxcosto = new javax.swing.JComboBox<>();
        TDS = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        StatusServ = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        PNDS = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TServ = new javax.swing.JTable();
        FPNDS = new javax.swing.JRadioButton();
        FPNDZ = new javax.swing.JRadioButton();
        Filtros = new javax.swing.JPanel();
        BStext = new javax.swing.JLabel();
        BS = new javax.swing.JTextField();
        BZtext = new javax.swing.JLabel();
        FiltroZ = new javax.swing.JComboBox<>();
        EliminarServ = new javax.swing.JButton();
        botonWeb2 = new botones.BotonWeb();
        TdServ = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TNDS = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        TStatusServ = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TServInt = new javax.swing.JTable();
        SAR = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Semcbx = new javax.swing.JComboBox<>();
        ADDservsem = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        TServTehua = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TServPte = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        NServSem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menuadm = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        ODT1 = new javax.swing.JMenuItem();
        CNQ1 = new javax.swing.JMenuItem();
        PRESQ1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        CDA1 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        CDA4 = new javax.swing.JMenuItem();
        ODT2 = new javax.swing.JMenuItem();
        LDA = new javax.swing.JMenuItem();
        LDA3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        General = new javax.swing.JMenuItem();
        Estadias = new javax.swing.JMenuItem();
        Torteria = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        Depositos = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        ADMV2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Zonas y Servicios");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Nombre de la zona");

        AgregarZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar.png"))); // NOI18N
        AgregarZ.setText("Agregar Zona");
        AgregarZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarZActionPerformed(evt);
            }
        });

        TablaZona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID de Zona", "Nombre Zona"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TablaZona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaZonaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaZona);

        jLabel2.setText("Buscar Zona:");

        BZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BZKeyReleased(evt);
            }
        });

        NZ.setColumns(20);
        NZ.setLineWrap(true);
        NZ.setRows(5);
        jScrollPane5.setViewportView(NZ);

        EliminarZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminarlogo.png"))); // NOI18N
        EliminarZ.setText("Eliminar");
        EliminarZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarZActionPerformed(evt);
            }
        });

        ModZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lapizmod.jpg"))); // NOI18N
        ModZ.setText("Modificar");
        ModZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(IDZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(AgregarZ)
                        .addGap(30, 30, 30)
                        .addComponent(ModZ)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BZ, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EliminarZ))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 534, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(BZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(EliminarZ))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(IDZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ModZ)
                            .addComponent(AgregarZ)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("Alta de Zonas", jScrollPane1);

        ZSh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        ZSh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ZShMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ZSh);

        jLabel3.setText("Nombre del servicio:");

        jLabel4.setText("Buscar Zona:");

        BZS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BZSKeyReleased(evt);
            }
        });

        AgrergarS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar.png"))); // NOI18N
        AgrergarS.setText("Agregar Servicio");
        AgrergarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgrergarSActionPerformed(evt);
            }
        });

        NS.setColumns(20);
        NS.setLineWrap(true);
        NS.setRows(5);
        jScrollPane6.setViewportView(NS);

        ModS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lapizmod.jpg"))); // NOI18N
        ModS.setText("Modificar");
        ModS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModSActionPerformed(evt);
            }
        });

        HorarioSYD.setText("Horario:");

        Sab.setText("Sabado");
        Sab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SabActionPerformed(evt);
            }
        });

        Horario.setText("L a V");

        Dom.setText("Domingo");
        Dom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DomActionPerformed(evt);
            }
        });

        jLabel11.setText("Cierra");

        Abre.setText("08:30");

        Cierra.setText("16:00");

        Allday.setText("24 Hrs");
        Allday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlldayActionPerformed(evt);
            }
        });

        jLabel14.setText("Supervisor:");

        Otro.setText("Otro horario");
        Otro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtroActionPerformed(evt);
            }
        });

        jLabel15.setText("Horario");

        jLabel16.setText("Abre");

        jLabel12.setText("Detalles de Servicio");

        jLabel5.setText("Tipo de Servicio:");

        jLabel6.setText("Costo:");

        Cbxcosto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "Con costo", "Sin costo" }));
        Cbxcosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxcostoItemStateChanged(evt);
            }
        });

        TDS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "SANTANDER CDMX", "SANTANDER FORANEOS", "EVENTOS", "SERVICIOS VP", "ESTACIONAMIENTOS CDMX", "ESTACIONAMIENTOS FORANEOS", "NO APLICA" }));

        jLabel7.setText("Status:");

        StatusServ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "Abierto", "Cerrado por Contingencia", "NO APLICA" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel11)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Supervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Horario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HorarioSYD, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SabadoT, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Sab))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(Dom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Allday))
                            .addComponent(DomingoT, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Otrotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Otro)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Abre, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Cierra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Cbxcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Costo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatusServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Supervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(Allday)
                    .addComponent(Otro)
                    .addComponent(Sab)
                    .addComponent(Dom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SabadoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DomingoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HorarioSYD)
                    .addComponent(Otrotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(Abre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Cierra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cbxcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(StatusServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(IDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NZS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(AgrergarS)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ModS))
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(279, 279, 279))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BZS, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(BZS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(idZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(NZS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AgrergarS)
                            .addComponent(ModS))
                        .addGap(53, 53, 53)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 121, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jTabbedPane1.addTab("Alta de Servicios", jScrollPane2);

        TServ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13"
            }
        ));
        TServ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TServMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(TServ);

        FPNDS.setText("Filtrar por nombre de servicio");
        FPNDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FPNDSActionPerformed(evt);
            }
        });

        FPNDZ.setText("Filtrar por Zonas");
        FPNDZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FPNDZActionPerformed(evt);
            }
        });

        BStext.setText("Buscar Servicio:");

        BS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BSKeyReleased(evt);
            }
        });

        BZtext.setText("Filtrar por Zonas:");

        FiltroZ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        FiltroZ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FiltroZItemStateChanged(evt);
            }
        });

        EliminarServ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminarlogo.png"))); // NOI18N
        EliminarServ.setText("Eliminar");
        EliminarServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarServActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FiltrosLayout = new javax.swing.GroupLayout(Filtros);
        Filtros.setLayout(FiltrosLayout);
        FiltrosLayout.setHorizontalGroup(
            FiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EliminarServ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BStext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BS, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BZtext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FiltroZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        FiltrosLayout.setVerticalGroup(
            FiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FiltrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BStext)
                    .addComponent(BS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BZtext)
                    .addComponent(FiltroZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EliminarServ))
                .addContainerGap())
        );

        botonWeb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Microsoft-Excel-Logo.png"))); // NOI18N
        botonWeb2.setLink("http://192.168.3.10/Reportes/Reporteservicios/EPCServicios.php");

        TdServ.setText("Total de servicios: ");

        TNDS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane7.setViewportView(TNDS);

        TStatusServ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(TStatusServ);

        javax.swing.GroupLayout PNDSLayout = new javax.swing.GroupLayout(PNDS);
        PNDS.setLayout(PNDSLayout);
        PNDSLayout.setHorizontalGroup(
            PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNDSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNDSLayout.createSequentialGroup()
                        .addComponent(Filtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(FPNDS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FPNDZ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonWeb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PNDSLayout.createSequentialGroup()
                        .addGroup(PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(PNDSLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(TdServ)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 2726, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))))
        );
        PNDSLayout.setVerticalGroup(
            PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNDSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Filtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botonWeb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FPNDS)
                            .addComponent(FPNDZ))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNDSLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TdServ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(393, 393, 393))
                    .addGroup(PNDSLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane8.setViewportView(PNDS);

        jTabbedPane1.addTab("Tabla Detallada de servicios", jScrollPane8);

        jLabel8.setText("Servicio a registrar:");

        TServInt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TServInt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TServIntMousePressed(evt);
            }
        });
        jScrollPane12.setViewportView(TServInt);

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Servicios en Iturbide.");

        jLabel13.setText("Semanal en el que aparecera el servicio:");

        Semcbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "puente titla", "iturbide", "tehuantepec" }));

        ADDservsem.setText("Agregar Servicio");
        ADDservsem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDservsemActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel17.setText("Servicios en Puente titla.");

        TServTehua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TServTehua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TServTehuaMousePressed(evt);
            }
        });
        jScrollPane13.setViewportView(TServTehua);

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel18.setText("Servicios en Tehuantepec.");

        TServPte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TServPte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TServPteMousePressed(evt);
            }
        });
        jScrollPane14.setViewportView(TServPte);

        jLabel19.setText("# Servicio:");

        NServSem.setEditable(false);
        NServSem.setText("0");

        jButton1.setText("Modificar Servicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ADDservsem)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Semcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 707, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(267, 267, 267))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NServSem, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SAR, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(260, 260, 260))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(253, 253, 253))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(NServSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(SAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(Semcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ADDservsem)
                            .addComponent(jButton1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel3);

        jTabbedPane1.addTab("Alta de Servicios semanales", jScrollPane11);

        Menuadm.setText("Todas las ventanas");

        jMenu5.setText("Area Nomina");

        jMenu6.setText("Nomina quincenal");

        ODT1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ODT1.setText("Ordenes de taller");
        ODT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ODT1ActionPerformed(evt);
            }
        });
        jMenu6.add(ODT1);

        CNQ1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CNQ1.setText("Nomina IMSS");
        CNQ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CNQ1ActionPerformed(evt);
            }
        });
        jMenu6.add(CNQ1);

        PRESQ1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        PRESQ1.setText("Prestamos");
        PRESQ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRESQ1ActionPerformed(evt);
            }
        });
        jMenu6.add(PRESQ1);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem8.setText("Nomina General");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        CDA1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CDA1.setText("Caja de ahorro");
        CDA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDA1ActionPerformed(evt);
            }
        });
        jMenu6.add(CDA1);

        jMenu5.add(jMenu6);

        jMenu7.setText("Semanal");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem9.setText("Nomina Semanal IMSS");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem10.setText("Prestamos Semanales");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem11.setText("Nomina Semanal General");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem11);

        CDA4.setText("Caja de ahorro");
        CDA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDA4ActionPerformed(evt);
            }
        });
        jMenu7.add(CDA4);

        ODT2.setText("Ordenes de taller");
        ODT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ODT2ActionPerformed(evt);
            }
        });
        jMenu7.add(ODT2);

        jMenu5.add(jMenu7);

        LDA.setText("Listas de asistencia C/IMSS ");
        LDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LDAActionPerformed(evt);
            }
        });
        jMenu5.add(LDA);

        LDA3.setText("Listas de asistencia S/IMSS");
        LDA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LDA3ActionPerformed(evt);
            }
        });
        jMenu5.add(LDA3);

        jMenu4.setText("Pre-nomina");

        jMenuItem20.setText("Semanal");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem20);

        jMenuItem21.setText("Quincenal");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem21);

        jMenu5.add(jMenu4);

        Menuadm.add(jMenu5);

        jMenu8.setText("Area RH");

        General.setText("Empleados General");
        General.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeneralActionPerformed(evt);
            }
        });
        jMenu8.add(General);

        Estadias.setText("Alumno de estadia");
        Estadias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadiasActionPerformed(evt);
            }
        });
        jMenu8.add(Estadias);

        Torteria.setText("Empleados Torteria");
        Torteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TorteriaActionPerformed(evt);
            }
        });
        jMenu8.add(Torteria);

        jMenu9.setText("Semanales");

        jMenuItem13.setText("Iturbide");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem13);

        jMenuItem14.setText("Tehuantepec");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem14);

        jMenuItem15.setText("PTE titla");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem15);

        jMenuItem17.setText("Generar vale de efectivo");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem17);

        jMenuItem18.setText("Reimprimir vale");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem18);

        jMenuItem19.setText("Generar padron");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem19);

        jMenu8.add(jMenu9);

        Depositos.setText("Depositos");

        jMenu10.setText("Quincenales");

        jMenuItem1.setText("Depositos C/ IMSS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem1);

        jMenuItem22.setText("Depositos S/ IMSS");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem22);

        jMenuItem24.setText("Santander Depositos C/ IMSS");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem24);

        jMenuItem25.setText("Santander Depositos S/ IMSS");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem25);

        Depositos.add(jMenu10);

        jMenu11.setText("Semanales");

        jMenuItem23.setText("Depositos C/ IMSS");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem23);

        jMenuItem16.setText("Depositos S/ IMSS");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem16);

        jMenuItem26.setText("Santander Depositos C/ IMSS");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem26);

        jMenuItem27.setText("Santander Depositos S/ IMSS");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem27);

        Depositos.add(jMenu11);

        jMenu8.add(Depositos);

        Menuadm.add(jMenu8);

        ADMV2.setText("Usuarios");
        ADMV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADMV2ActionPerformed(evt);
            }
        });
        Menuadm.add(ADMV2);

        jMenuItem7.setText("Edicion Pagos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        Menuadm.add(jMenuItem7);

        jMenuItem12.setText("Pensiones");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        Menuadm.add(jMenuItem12);

        jMenuBar1.add(Menuadm);

        jMenu1.setText("Seguridad.");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IcoCDU.png"))); // NOI18N
        jMenuItem2.setText("Cambiar de usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FiltroZItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FiltroZItemStateChanged
        // TODO add your handling code here:
        mostrarServicios();
    }//GEN-LAST:event_FiltroZItemStateChanged

    private void BSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BSKeyReleased
        // TODO add your handling code here:
        mostrarServicios();
    }//GEN-LAST:event_BSKeyReleased

    private void FPNDZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FPNDZActionPerformed
        // TODO add your handling code here:
        Filtros.setVisible(true);
        BZtext.setVisible(true);
        FiltroZ.setVisible(true);
        FiltroZ.setSelectedIndex(0);
        BS.setVisible(false);
        BS.setText("");
        BStext.setVisible(false);
    }//GEN-LAST:event_FPNDZActionPerformed

    private void FPNDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FPNDSActionPerformed
        // TODO add your handling code here:
        Filtros.setVisible(true);
        BZtext.setVisible(false);
        FiltroZ.setVisible(false);
        FiltroZ.setSelectedIndex(0);
        BS.setVisible(true);
        BS.setText("");
        BStext.setVisible(true);
    }//GEN-LAST:event_FPNDSActionPerformed

    private void TServMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TServMouseClicked
        try {
            // TODO add your handling code here:
            int seleccionar = TServ.getSelectedRow();
            IDS.setText(String.valueOf(TServ.getValueAt(seleccionar, 0)));
            idZona.setText(String.valueOf(TServ.getValueAt(seleccionar, 1)));
            NZS.setText(String.valueOf(TServ.getValueAt(seleccionar, 2)));
            Supervisor.setText(String.valueOf(TServ.getValueAt(seleccionar, 3)));
            NS.setText(String.valueOf(TServ.getValueAt(seleccionar, 4)));
            Horario.setText(String.valueOf(TServ.getValueAt(seleccionar, 5)));
            Abre.setText(String.valueOf(TServ.getValueAt(seleccionar, 6)));
            Cierra.setText(String.valueOf(TServ.getValueAt(seleccionar, 7)));
            SabadoT.setText(String.valueOf(TServ.getValueAt(seleccionar, 8)));
            DomingoT.setText(String.valueOf(TServ.getValueAt(seleccionar, 9)));
            Otrotxt.setText(String.valueOf(TServ.getValueAt(seleccionar, 10)));
            DefaultTableModel model = (DefaultTableModel) TServ.getModel();

            String combo1 = model.getValueAt(seleccionar, 11).toString();
            for (int i = 0; i < TDS.getItemCount(); i++) {
                if (TDS.getItemAt(i).equalsIgnoreCase(combo1)) {
                    TDS.setSelectedIndex(i);
                }
            }

            Costo.setText(String.valueOf(TServ.getValueAt(seleccionar, 12)));

            String combo3 = model.getValueAt(seleccionar, 13).toString();
            for (int i = 0; i < StatusServ.getItemCount(); i++) {
                if (StatusServ.getItemAt(i).equalsIgnoreCase(combo3)) {
                    StatusServ.setSelectedIndex(i);
                }
            }

            int id = Integer.parseInt(TServ.getValueAt(seleccionar, 0).toString());
            String SabadoB = SabadoT.getText();
            String DomingoB = DomingoT.getText();
            String OtroB = Otrotxt.getText();
            String CostoB = Costo.getText();

            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement("select * from servicio where idServ =?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            java.sql.Statement st = con.createStatement();
            while (rs.next()) {

                if (rs.getString("Sabado").equals("OFF")) {
                    Sab.setSelected(false);
                    SabadoT.setEnabled(false);
                } else if (rs.getString("Sabado").equals(SabadoB)) {
                    Sab.setSelected(true);
                    SabadoT.setEnabled(true);
                    Horario.setEnabled(true);
                    Cierra.setEnabled(true);
                    Abre.setEnabled(true);
                } else if (rs.getString("Sabado").equals("")) {
                    Sab.setSelected(false);
                    SabadoT.setEnabled(false);
                }
                if (rs.getString("Domingo").equals("OFF")) {
                    Dom.setSelected(false);
                    DomingoT.setEnabled(false);
                } else if (rs.getString("Domingo").equals(DomingoB)) {
                    Dom.setSelected(true);
                    DomingoT.setEnabled(true);
                    Horario.setEnabled(true);
                    Cierra.setEnabled(true);
                    Abre.setEnabled(true);
                } else if (rs.getString("Domingo").equals("")) {
                    Dom.setSelected(false);
                    DomingoT.setEnabled(false);
                }
                if (rs.getString("Horario").equals("24 HRS")) {
                    Allday.setSelected(true);
                    Otro.setSelected(false);
                    Otrotxt.setEnabled(false);
                    Otrotxt.setText("");
                    Dom.setSelected(false);
                    DomingoT.setEnabled(false);
                    DomingoT.setText("");
                    Sab.setSelected(false);
                    SabadoT.setEnabled(false);
                    SabadoT.setText("");
                    Horario.setText("");
                    Horario.setEnabled(false);
                    Abre.setText("");
                    Cierra.setText("");
                    Abre.setEnabled(false);
                    Cierra.setEnabled(false);
                }
                if (rs.getString("Otro").equals("")) {
                    Otro.setSelected(false);
                    Otrotxt.setEnabled(false);
                } else if (rs.getString("Otro").equals(OtroB)) {
                    Otro.setSelected(true);
                    Otrotxt.setEnabled(true);
                    Allday.setSelected(false);
                    Dom.setSelected(false);
                    DomingoT.setEnabled(false);
                    DomingoT.setText("");
                    Sab.setSelected(false);
                    SabadoT.setEnabled(false);
                    SabadoT.setText("");
                    Horario.setText("");
                    Horario.setEnabled(false);
                    Abre.setText("");
                    Cierra.setText("");
                    Abre.setEnabled(false);
                    Cierra.setEnabled(false);
                }
                if (rs.getString("Costo").equals("Sin costo")) {
                    Cbxcosto.setSelectedIndex(2);
                    Costo.setEnabled(false);
                    Costo.setText("");
                } else if (rs.getString("Costo").equals("")) {
                    Cbxcosto.setSelectedIndex(0);
                    Costo.setEnabled(false);
                } else if (rs.getString("Costo").equals(CostoB)) {
                    Cbxcosto.setSelectedIndex(1);
                    Costo.setEnabled(true);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(AltasZyS_3.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_TServMouseClicked

    private void OtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtroActionPerformed
        // TODO add your handling code here:
        if (Otro.isSelected() == true) {
            Otrotxt.setEnabled(true);
            Dom.setSelected(false);
            DomingoT.setEnabled(false);
            DomingoT.setText("");
            Sab.setSelected(false);
            SabadoT.setEnabled(false);
            SabadoT.setText("");
            Allday.setSelected(false);
            Horario.setText("");
            Abre.setText("");
            Cierra.setText("");
            Abre.setEnabled(false);
            Cierra.setEnabled(false);
            Horario.setEnabled(false);
        } else if (Otro.isSelected() == false) {
            Otrotxt.setEnabled(false);
            Horario.setEnabled(true);
            Horario.setText("L a V");
            Abre.setEnabled(true);
            Cierra.setEnabled(true);
        }
    }//GEN-LAST:event_OtroActionPerformed

    private void AlldayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlldayActionPerformed
        // TODO add your handling code here:
        if (Allday.isSelected() == true) {
            Otro.setSelected(false);
            Otrotxt.setEnabled(false);
            Otrotxt.setText("");
            Dom.setSelected(false);
            DomingoT.setEnabled(false);
            DomingoT.setText("");
            Sab.setSelected(false);
            SabadoT.setEnabled(false);
            SabadoT.setText("");
            Horario.setText("");
            Horario.setEnabled(false);
            Abre.setText("");
            Cierra.setText("");
            Abre.setEnabled(false);
            Cierra.setEnabled(false);
        } else if (Allday.isSelected() == false) {
            Otrotxt.setEnabled(false);
            Horario.setEnabled(true);
            Horario.setText("L a V");
            Abre.setEnabled(true);
            Cierra.setEnabled(true);
        }
    }//GEN-LAST:event_AlldayActionPerformed

    private void DomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DomActionPerformed
        // TODO add your handling code here:
        if (Dom.isSelected() == true) {
            DomingoT.setEnabled(true);
            Otro.setSelected(false);
            Otrotxt.setEnabled(false);
            Allday.setSelected(false);
            Otrotxt.setText("");
            Horario.setEnabled(true);
            Horario.setText("L a V");
            Abre.setEnabled(true);
            Cierra.setEnabled(true);
        } else if (Dom.isSelected() == false) {
            DomingoT.setEnabled(false);
            Horario.setEnabled(true);
            Horario.setText("L a V");
            DomingoT.setText("");

        }
    }//GEN-LAST:event_DomActionPerformed

    private void SabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SabActionPerformed
        // TODO add your handling code here:
        if (Sab.isSelected() == true) {
            SabadoT.setEnabled(true);
            Otro.setSelected(false);
            Otrotxt.setEnabled(false);
            Allday.setSelected(false);
            Otrotxt.setText("");
            Horario.setEnabled(true);
            Horario.setText("L a V");
            Abre.setEnabled(true);
            Cierra.setEnabled(true);

        } else if (Sab.isSelected() == false) {
            SabadoT.setEnabled(false);
            Horario.setEnabled(true);
            Horario.setText("L a V");
            SabadoT.setText("");

        }

    }//GEN-LAST:event_SabActionPerformed

    private void ModSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModSActionPerformed
        // TODO add your handling code here:
        modS();
        clearServ();
    }//GEN-LAST:event_ModSActionPerformed

    private void AgrergarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgrergarSActionPerformed
        // TODO add your handling code here:
        AgregarS();
        clearServ();
    }//GEN-LAST:event_AgrergarSActionPerformed

    private void BZSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BZSKeyReleased
        // TODO add your handling code here:
        compartirZ();
    }//GEN-LAST:event_BZSKeyReleased

    private void ZShMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ZShMouseClicked
        // TODO add your handling code here:
        int seleccionar = ZSh.getSelectedRow();
        idZona.setText(String.valueOf(ZSh.getValueAt(seleccionar, 0)));
        NZS.setText(String.valueOf(ZSh.getValueAt(seleccionar, 1)));
    }//GEN-LAST:event_ZShMouseClicked

    private void ModZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModZActionPerformed
        // TODO add your handling code here:
        modZ();
        FiltrosZonas zz = new FiltrosZonas();
        DefaultComboBoxModel modelzonas = new DefaultComboBoxModel(zz.mostrarzonas());
        FiltroZ.setModel(modelzonas);
    }//GEN-LAST:event_ModZActionPerformed

    private void EliminarZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarZActionPerformed
        // TODO add your handling code here:
        eliminarZ();
    }//GEN-LAST:event_EliminarZActionPerformed

    private void BZKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BZKeyReleased
        // TODO add your handling code here:
        mostrarzonas();
    }//GEN-LAST:event_BZKeyReleased

    private void TablaZonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaZonaMouseClicked
        // TODO add your handling code here:
        int seleccionar = TablaZona.getSelectedRow();
        IDZ.setText(String.valueOf(TablaZona.getValueAt(seleccionar, 0)));
        NZ.setText(String.valueOf(TablaZona.getValueAt(seleccionar, 1)));
    }//GEN-LAST:event_TablaZonaMouseClicked

    private void AgregarZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarZActionPerformed
        // TODO add your handling code here:
        AgregarZ();
        FiltrosZonas zz = new FiltrosZonas();
        DefaultComboBoxModel modelzonas = new DefaultComboBoxModel(zz.mostrarzonas());
        FiltroZ.setModel(modelzonas);
    }//GEN-LAST:event_AgregarZActionPerformed

    private void EliminarServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarServActionPerformed
        // TODO add your handling code here:
        eliminarS();
    }//GEN-LAST:event_EliminarServActionPerformed

    private void CbxcostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxcostoItemStateChanged
        // TODO add your handling code here:
        String Q = (String) Cbxcosto.getSelectedItem();
        if (Q.equals(",")) {
            Costo.setVisible(false);
            Costo.setText("");

        }
        if (Q.equals("Con costo")) {
            Costo.setVisible(true);
            Costo.setText("");

        }
        if (Q.equals("Sin costo")) {
            Costo.setVisible(false);
            Costo.setText("");
        }
    }//GEN-LAST:event_CbxcostoItemStateChanged

    private void ODT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ODT1ActionPerformed
        ODTQ_5 regr = new ODTQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ODT1ActionPerformed

    private void CNQ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CNQ1ActionPerformed
        NominaQ_5 regr = new NominaQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CNQ1ActionPerformed

    private void PRESQ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRESQ1ActionPerformed
        PresQ_5 regr = new PresQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_PRESQ1ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        NominaQSiMSS_5 regr = new NominaQSiMSS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void CDA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDA1ActionPerformed
        CDAQ_5 regr = new CDAQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CDA1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        NominaS_5 regr = new NominaS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        PresS_5 regr = new PresS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        NominaS_simss_5 regr = new NominaS_simss_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void GeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeneralActionPerformed

        Empleados_4 RH = new Empleados_4(usr, LP);
        RH.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_GeneralActionPerformed

    private void EstadiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadiasActionPerformed
        Estadias_4 regr = new Estadias_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_EstadiasActionPerformed

    private void TorteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TorteriaActionPerformed
        Tortas_4 regr = new Tortas_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_TorteriaActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        Iturbide_4 regr = new Iturbide_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        Tehuantepec_4 regr = new Tehuantepec_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        PT_4 regr = new PT_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void ADMV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADMV2ActionPerformed
        VentanaADM_3 regr = new VentanaADM_3(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ADMV2ActionPerformed

    private void CDA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDA4ActionPerformed
        CDAS_5 regr = new CDAS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CDA4ActionPerformed

    private void ODT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ODT2ActionPerformed
        ODTS_5 regr = new ODTS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ODT2ActionPerformed

    private void ADDservsemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDservsemActionPerformed
        AgregarServSem();
    }//GEN-LAST:event_ADDservsemActionPerformed

    private void TServIntMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TServIntMousePressed
        int seleccionar = TServInt.getSelectedRow();
        NServSem.setText(String.valueOf(TServInt.getValueAt(seleccionar, 0)));
        SAR.setText(String.valueOf(TServInt.getValueAt(seleccionar, 1)));
        Semcbx.setSelectedIndex(2);
    }//GEN-LAST:event_TServIntMousePressed

    private void TServPteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TServPteMousePressed
        int seleccionar = TServPte.getSelectedRow();
        NServSem.setText(String.valueOf(TServPte.getValueAt(seleccionar, 0)));
        SAR.setText(String.valueOf(TServPte.getValueAt(seleccionar, 1)));
        Semcbx.setSelectedIndex(1);
    }//GEN-LAST:event_TServPteMousePressed

    private void TServTehuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TServTehuaMousePressed
        int seleccionar = TServTehua.getSelectedRow();
        NServSem.setText(String.valueOf(TServTehua.getValueAt(seleccionar, 0)));
        SAR.setText(String.valueOf(TServTehua.getValueAt(seleccionar, 1)));
        Semcbx.setSelectedIndex(3);
    }//GEN-LAST:event_TServTehuaMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ModsServSem();
        MostrarServSem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void LDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LDAActionPerformed
        Listas_CI_5 regr = new Listas_CI_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LDAActionPerformed

    private void LDA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LDA3ActionPerformed
        Listas_SI_5 regr = new Listas_SI_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LDA3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        EdicionPagos_3 regr = new EdicionPagos_3(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        PensionesVPIturbide_7 regr = new PensionesVPIturbide_7(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        VDE regr = new VDE(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        Rvales regr = new Rvales(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        Padrones regr = new Padrones(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        PrenominaS regr = new PrenominaS();
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        PremonimaQ regr = new PremonimaQ();
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DepositosQ_4 regr = new DepositosQ_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        DepositosQ_SIMSS_4 regr = new DepositosQ_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        DepositosQSan_4 regr = new DepositosQSan_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        DepositosQsan_SIMSS_4 regr = new DepositosQsan_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        DepositosS_4 regr = new DepositosS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        DepositosS_SIMSS_4 regr = new DepositosS_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        DepositosSSan_4 regr = new DepositosSSan_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        DepositosSSan_SIMSS_4 regr = new DepositosSSan_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        int i = JOptionPane.showConfirmDialog(this, "El cambiar de usuario cerrara la ventana actual. \n ¿Seguir con esta accion?");
        if (i == 0) {
            Inicio_1 regr = new Inicio_1();
            regr.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltasZyS_3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AltasZyS_3().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADDservsem;
    private javax.swing.JMenuItem ADMV2;
    private javax.swing.JTextField Abre;
    private javax.swing.JButton AgregarZ;
    private javax.swing.JButton AgrergarS;
    private javax.swing.JRadioButton Allday;
    private javax.swing.JTextField BS;
    private javax.swing.JLabel BStext;
    private javax.swing.JTextField BZ;
    private javax.swing.JTextField BZS;
    private javax.swing.JLabel BZtext;
    private javax.swing.JMenuItem CDA1;
    private javax.swing.JMenuItem CDA4;
    private javax.swing.JMenuItem CNQ1;
    private javax.swing.JComboBox<String> Cbxcosto;
    private javax.swing.JTextField Cierra;
    private javax.swing.JTextField Costo;
    private javax.swing.JMenu Depositos;
    private javax.swing.JRadioButton Dom;
    private javax.swing.JTextField DomingoT;
    private javax.swing.JButton EliminarServ;
    private javax.swing.JButton EliminarZ;
    private javax.swing.JMenuItem Estadias;
    private javax.swing.JRadioButton FPNDS;
    private javax.swing.JRadioButton FPNDZ;
    private javax.swing.JComboBox<String> FiltroZ;
    private javax.swing.JPanel Filtros;
    private javax.swing.JMenuItem General;
    private javax.swing.JTextField Horario;
    private javax.swing.JLabel HorarioSYD;
    private javax.swing.JTextField IDS;
    private javax.swing.JTextField IDZ;
    private javax.swing.JMenuItem LDA;
    private javax.swing.JMenuItem LDA3;
    private javax.swing.JMenu Menuadm;
    private javax.swing.JButton ModS;
    private javax.swing.JButton ModZ;
    private javax.swing.JTextArea NS;
    private javax.swing.JTextField NServSem;
    private javax.swing.JTextArea NZ;
    private javax.swing.JTextField NZS;
    private javax.swing.JMenuItem ODT1;
    private javax.swing.JMenuItem ODT2;
    private javax.swing.JRadioButton Otro;
    private javax.swing.JTextField Otrotxt;
    private javax.swing.JPanel PNDS;
    private javax.swing.JMenuItem PRESQ1;
    private javax.swing.JTextField SAR;
    private javax.swing.JRadioButton Sab;
    private javax.swing.JTextField SabadoT;
    private javax.swing.JComboBox<String> Semcbx;
    private javax.swing.JComboBox<String> StatusServ;
    private javax.swing.JTextField Supervisor;
    private javax.swing.JComboBox<String> TDS;
    private javax.swing.JTable TNDS;
    private javax.swing.JTable TServ;
    private javax.swing.JTable TServInt;
    private javax.swing.JTable TServPte;
    private javax.swing.JTable TServTehua;
    private javax.swing.JTable TStatusServ;
    private javax.swing.JTable TablaZona;
    private javax.swing.JLabel TdServ;
    private javax.swing.JMenuItem Torteria;
    private javax.swing.JTable ZSh;
    private botones.BotonWeb botonWeb2;
    private javax.swing.JTextField idZona;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
