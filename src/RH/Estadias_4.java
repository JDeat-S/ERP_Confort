package RH;

import Nomina.Listas.*;
import Semanal.PT_4;
import Semanal.Tehuantepec_4;
import Semanal.Iturbide_4;
import Nomina.ModulosS.CDAS_5;
import Nomina.ModulosS.PresS_5;
import Nomina.ModulosS.ODTS_5;
import Nomina.ModulosQ.CDAQ_5;
import Nomina.ModulosQ.ODTQ_5;
import Nomina.ModulosQ.PresQ_5;
import Admin.*;
import Conexion.ConexionSQL;
import Inicio.Inicio_1;
import Logicas.*;
import Nomina.*;
import RH.Depositos.DepositosQ_4;
import RH.Depositos.DepositosQ_SIMSS_4;
import RH.Depositos.DepositosS_4;
import RH.Depositos.DepositosS_SIMSS_4;
import RH.Depositos.Santander.DepositosQSan_4;
import RH.Depositos.Santander.DepositosQsan_SIMSS_4;
import RH.Depositos.Santander.DepositosSSan_4;
import RH.Depositos.Santander.DepositosSSan_SIMSS_4;import Semanal.Padrones.Padrones;
import Semanal.Vales.VDE;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JDeat
 */
public final class Estadias_4 extends javax.swing.JFrame {

    ConexionSQL cc = new ConexionSQL();
    Connection con = cc.conexion();
    Logica_usuarios usr;
    Logica_permisos LP;

    public Estadias_4() {
        initComponents();
        this.setExtendedState(6);
        this.setLocationRelativeTo(null);
        LabelF1.setVisible(false);
        FilAP.setVisible(false);
        FilAM.setVisible(false);
        Filname.setVisible(false);
        MDE();
        setIconImage(new ImageIcon(Estadias_4.class.getClassLoader().getResource("Imagenes/Icono.png")).getImage());

    }

    public Estadias_4(Logica_usuarios usr, Logica_permisos LP) {
        initComponents();
        this.usr = usr;
        this.LP = LP;
        this.setExtendedState(6);
        this.setLocationRelativeTo(null);
        LabelF1.setVisible(false);
        FilAP.setVisible(false);
        FilAM.setVisible(false);
        Filname.setVisible(false);
        MDE();
        setIconImage(new ImageIcon(Estadias_4.class.getClassLoader().getResource("Imagenes/Icono.png")).getImage());
        setTitle("Ventana Alumnos estadias # Usuario: " + usr.getId_user() + " " + usr.getApellidop() + " " + usr.getApellidoM() + " " + usr.getNombre()
                + " Tipo de ususario: " + usr.getNombre_tipo() + " Usuario: " + usr.getUsuario());
       

        switch (LP.getVDA()) {
            case 0 -> {
            }
            case 1 -> {
                Menuadm.setVisible(false);
                /*if (LP.getP1() == 0) {
                Alumnos.setVisible(false);
                }*/
                if (LP.getP2() == 0) {
                    EmpleadosT.setVisible(false);
                }   if (LP.getP3() == 0) {
                    Depositos.setVisible(false);
                }   if (LP.getP4() == 0) {
                    Semanales.setVisible(false);
                }
            }
            case 2 -> {
                Menuadm.setVisible(false);
                /*if (LP.getP1() == 0) {
                Alumnos.setVisible(false);
                }*/
                if (LP.getP2() == 0) {
                    EmpleadosT.setVisible(false);
                }   if (LP.getP3() == 0) {
                    Depositos.setVisible(false);
                }   if (LP.getP4() == 0) {
                    Semanales.setVisible(false);
                }
            }
            default -> {
            }
        }
    }

    @SuppressWarnings("unchecked")
    //limpiarest
    private void cleanest() {
        TDCest.setSelectedIndex(0);
        Statusest.setSelectedIndex(0);
        Cdpest.setSelected(false);
        Cdaest.setSelected(false);
        Pdaest.setSelected(false);
        Paest.setSelected(false);
        Saest.setSelected(false);
        Taest.setSelected(false);
        Ctest.setSelected(false);
        Adeest.setSelected(false);
        Evest.setSelected(false);
        NexpEst.setText("0");
        APEst.setText("");
        AMest.setText("");
        nameest.setText("");
        NCest.setText("");
        Carrest.setText("");
        Curpest.setText("");
        Fdiest.setText("");
        Fdtest.setText("");
        Obsest.setText("");
    }
//editar

    private void EditarEst() {

        String TDC = TDCest.getSelectedItem().toString();
        String STAT = Statusest.getSelectedItem().toString();
        String Cdp = null;
        if (Cdpest.isSelected() == true) {
            Cdp = "x";
        } else if (Cdpest.isSelected() == false) {
            Cdp = "";
        }
        String Cda = null;
        if (Cdaest.isSelected() == true) {
            Cda = "x";
        } else if (Cdaest.isSelected() == false) {
            Cda = "";
        }
        String Pda = null;
        if (Pdaest.isSelected() == true) {
            Pda = "x";
        } else if (Pdaest.isSelected() == false) {
            Pda = "";
        }
        String Pa = null;
        if (Paest.isSelected() == true) {
            Pa = "x";
        } else if (Paest.isSelected() == false) {
            Pa = "";
        }
        String Sa = null;
        if (Saest.isSelected() == true) {
            Sa = "x";
        } else if (Saest.isSelected() == false) {
            Sa = "";
        }
        String Ta = null;
        if (Taest.isSelected() == true) {
            Ta = "x";
        } else if (Taest.isSelected() == false) {
            Ta = "";
        }
        String Ct = null;
        if (Ctest.isSelected() == true) {
            Ct = "x";
        } else if (Ctest.isSelected() == false) {
            Ct = "";
        }
        String Ade = null;
        if (Adeest.isSelected() == true) {
            Ade = "x";
        } else if (Adeest.isSelected() == false) {
            Ade = "";
        }
        String Ev = null;
        if (Evest.isSelected() == true) {
            Ev = "x";
        } else if (Evest.isSelected() == false) {
            Ev = "";
        }
        String SQL = " UPDATE `confort`.`estadia` SET `idEstadias` = ?, "
                + "`Apellido P` = ?, `Apellido M` = ?, `Nombre(s)` = ?,"
                + " `# Celular` = ?, `Carrera` = ?, `Tipo de carrera` = ?, `CURP` = ?,"
                + " `Status` = ?, `Fecha de ingreso` = ?, `Fecha de termino` = ?,"
                + " `Carta de precentacion` = ?, `Carta de aceptacion` = ?,"
                + " `Programa de actividades` = ?, `1er avance` = ?,"
                + " `2do avance` = ?, `3er avance` = ?, `Carta termino` = ?,"
                + " `Autorizacion empastado` = ?, `Evaluacion` = ?, `Observaciones` = ?"
                + " WHERE (`idEstadias` = ?)";
        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setInt(1, Integer.parseInt(NexpEst.getText()));
            pst.setString(2, APEst.getText());
            pst.setString(3, AMest.getText());
            pst.setString(4, nameest.getText());
            pst.setString(5, NCest.getText());
            pst.setString(6, Carrest.getText());
            pst.setString(7, TDC);
            pst.setString(8, Curpest.getText());
            pst.setString(9, STAT);
            pst.setString(10, Fdiest.getText());
            pst.setString(11, Fdtest.getText());
            pst.setString(12, Cdp);
            pst.setString(13, Cda);
            pst.setString(14, Pda);
            pst.setString(15, Pa);
            pst.setString(16, Sa);
            pst.setString(17, Ta);
            pst.setString(18, Ct);
            pst.setString(19, Ade);
            pst.setString(20, Ev);
            pst.setString(21, Obsest.getText());
            pst.setInt(22, Integer.parseInt(NexpEst.getText()));

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estadia Modificada");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar Estadia: " + e.getMessage());
        }

    }
//agregar

    public void AgregarE() {
        String TDC = TDCest.getSelectedItem().toString();
        String STAT = Statusest.getSelectedItem().toString();
        String Cdp = null;
        if (Cdpest.isSelected() == true) {
            Cdp = "x";
        } else if (Cdpest.isSelected() == false) {
            Cdp = "";
        }
        String Cda = null;
        if (Cdaest.isSelected() == true) {
            Cda = "x";
        } else if (Cdaest.isSelected() == false) {
            Cda = "";
        }
        String Pda = null;
        if (Pdaest.isSelected() == true) {
            Pda = "x";
        } else if (Pdaest.isSelected() == false) {
            Pda = "";
        }
        String Pa = null;
        if (Paest.isSelected() == true) {
            Pa = "x";
        } else if (Paest.isSelected() == false) {
            Pa = "";
        }
        String Sa = null;
        if (Saest.isSelected() == true) {
            Sa = "x";
        } else if (Saest.isSelected() == false) {
            Sa = "";
        }
        String Ta = null;
        if (Taest.isSelected() == true) {
            Ta = "x";
        } else if (Taest.isSelected() == false) {
            Ta = "";
        }
        String Ct = null;
        if (Ctest.isSelected() == true) {
            Ct = "x";
        } else if (Ctest.isSelected() == false) {
            Ct = "";
        }
        String Ade = null;
        if (Adeest.isSelected() == true) {
            Ade = "x";
        } else if (Adeest.isSelected() == false) {
            Ade = "";
        }
        String Ev = null;
        if (Evest.isSelected() == true) {
            Ev = "x";
        } else if (Evest.isSelected() == false) {
            Ev = "";
        }
        String SQL = "INSERT INTO `confort`.`estadia` (`idEstadias`, `Apellido P`,"
                + " `Apellido M`, `Nombre(s)`, `# Celular`, `Carrera`, "
                + "`Tipo de carrera`, `CURP`, `Status`, `Fecha de ingreso`, "
                + "`Fecha de termino`, `Carta de precentacion`, `Carta de aceptacion`,"
                + " `Programa de actividades`, `1er avance`, `2do avance`, `3er avance`, "
                + "`Carta termino`, `Autorizacion empastado`, `Evaluacion`, `Observaciones`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(SQL);

            pst.setInt(1, Integer.parseInt(NexpEst.getText()));
            pst.setString(2, APEst.getText());
            pst.setString(3, AMest.getText());
            pst.setString(4, nameest.getText());
            pst.setString(5, NCest.getText());
            pst.setString(6, Carrest.getText());
            pst.setString(7, TDC);
            pst.setString(8, Curpest.getText());
            pst.setString(9, STAT);
            pst.setString(10, Fdiest.getText());
            pst.setString(11, Fdtest.getText());
            pst.setString(12, Cdp);
            pst.setString(13, Cda);
            pst.setString(14, Pda);
            pst.setString(15, Pa);
            pst.setString(16, Sa);
            pst.setString(17, Ta);
            pst.setString(18, Ct);
            pst.setString(19, Ade);
            pst.setString(20, Ev);
            pst.setString(21, Obsest.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estadia agregado.");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar en Estadia: " + e.getMessage());
        }
    }

    //mostrar datos de estadia
    public void MDE() {
        //Buscar estadias
        String FiltroNGe = Filname.getText();
        String FAP = FilAP.getText();
        String FAM = FilAM.getText();
        String where = "select * from estadia";
        if (!"".equals(FiltroNGe)) {
            where = "Select * from estadia where `Nombre(s)` LIKE '%" + FiltroNGe + "%'";
        } else if (!"".equals(FAP)) {
            where = "Select * from estadia where `Apellido P` LIKE '%" + FAP + "%'";
        } else if (!"".equals(FAM)) {
            where = "Select * from estadia where `Apellido M` LIKE '%" + FAM + "%'";
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
            Testadia.setModel(modelo);
            PreparedStatement ps;
            ResultSet rs;

            ps = con.prepareStatement(where);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("# Exp");
            modelo.addColumn("Apellido P");//3
            modelo.addColumn("Apellido M");
            modelo.addColumn("Nombre(s)");//5
            modelo.addColumn("# Celular");//5
            modelo.addColumn("Carrera");
            modelo.addColumn("Tipo de carrera");//7
            modelo.addColumn("CURP");
            modelo.addColumn("Status");//9
            modelo.addColumn("Fecha de ingreso");//10
            modelo.addColumn("Fecha de termino");//11
            modelo.addColumn("Carta de precentacion");//12
            modelo.addColumn("Carta de aceptacion");//13
            modelo.addColumn("Programa de actividades");//14
            modelo.addColumn("1er avance");
            modelo.addColumn("2do avance");//16
            modelo.addColumn("3er avance");
            modelo.addColumn("Carta termino");//18
            modelo.addColumn("Autorizacion de empastado");
            modelo.addColumn("Evaluacion");//20
            modelo.addColumn("Observaciones");

            int[] anchos = { /*Exp*/35, /*ap*/ 70, /*am*/ 70, /*name*/ 100, /*celular*/ 65,
                /*Carr*/ 65, /*TDC*/ 65, /*Curp*/ 65, /*Status*/ 50,
                /*FDI*/ 75, /*FDT*/ 75, /*CDP*/ 75, /*CDA*/ 75, /*PDA*/ 95, /*1*/ 40, /*2*/ 40, /*3*/ 40, /*CT*/ 40,
                /*ADE*/ 100, /*Ev*/ 30, /*OBS*/ 1000};

            for (int x = 0; x < cantidadColumnas; x++) {
                //Nombre tabla
                Testadia.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);

            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Datos de Tabla Estadia: " + e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        nameest = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        NCest = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Curpest = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Carrest = new javax.swing.JTextField();
        TDCest = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        NexpEst = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        APEst = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Statusest = new javax.swing.JComboBox<>();
        AMest = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        Fdiest = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Fdtest = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Cdpest = new javax.swing.JCheckBox();
        Cdaest = new javax.swing.JCheckBox();
        Pdaest = new javax.swing.JCheckBox();
        Paest = new javax.swing.JCheckBox();
        Saest = new javax.swing.JCheckBox();
        Taest = new javax.swing.JCheckBox();
        Ctest = new javax.swing.JCheckBox();
        Adeest = new javax.swing.JCheckBox();
        Evest = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Obsest = new javax.swing.JTextArea();
        add = new javax.swing.JButton();
        mod = new javax.swing.JButton();
        Cs = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        Filtroest = new javax.swing.JComboBox<>();
        LabelF1 = new javax.swing.JLabel();
        Filname = new javax.swing.JTextField();
        FilAP = new javax.swing.JTextField();
        FilAM = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        Testadia = new javax.swing.JTable();
        botonWeb1 = new botones.BotonWeb();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menuadm = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        ODT = new javax.swing.JMenuItem();
        CNQ = new javax.swing.JMenuItem();
        PRESQ = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        CDA = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        CDA4 = new javax.swing.JMenuItem();
        ODT2 = new javax.swing.JMenuItem();
        LDA = new javax.swing.JMenuItem();
        LDA3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        General1 = new javax.swing.JMenuItem();
        Estadias = new javax.swing.JMenuItem();
        Torteria = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        Depositos1 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        ZYS = new javax.swing.JMenuItem();
        ADMV1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        General = new javax.swing.JMenuItem();
        EmpleadosT = new javax.swing.JMenuItem();
        Depositos = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        Semanales = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Nombre(s):");

        jLabel5.setText("# Celular:");

        jLabel10.setText("CURP:");

        jLabel8.setText("Status:");

        jLabel6.setText("Carrera:");

        TDCest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "TSU", "Licenciatura", "Ingenieria" }));

        jLabel1.setText("# Exp:");

        NexpEst.setText("0");

        jLabel2.setText("Apellido P:");

        jLabel3.setText("Apellido M:");

        jLabel7.setText("Tipo de carrera:");

        Statusest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "Vigente", "Pendiente", "Baja" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NCest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(APEst, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AMest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(NexpEst, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Curpest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Carrest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TDCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Statusest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NexpEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(APEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(AMest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nameest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(NCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Curpest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Carrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TDCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Statusest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Fecha de ingreso:");

        jLabel11.setText("Fecha de termino:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fdiest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fdtest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Fdiest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Fdtest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Documentos");

        Cdpest.setText("Carta de presentacion");

        Cdaest.setText("Carta de aceptacion");

        Pdaest.setText("Programa de actividades");

        Paest.setText("1er avance");

        Saest.setText("2do avance");

        Taest.setText("3er avance");

        Ctest.setText("Carta termino");

        Adeest.setText("Autorizacion del empaztado");

        Evest.setText("Evaluacion.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cdaest)
                            .addComponent(Cdpest)
                            .addComponent(Pdaest)
                            .addComponent(Paest)
                            .addComponent(Saest)
                            .addComponent(Taest)
                            .addComponent(Ctest)
                            .addComponent(Adeest)
                            .addComponent(Evest)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel12)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cdpest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cdaest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pdaest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Paest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Saest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Taest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ctest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Adeest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Evest)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel13.setText("Observaciones:");

        Obsest.setColumns(20);
        Obsest.setRows(5);
        jScrollPane3.setViewportView(Obsest);

        add.setText("Agregar");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        mod.setText("Modificar");
        mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modActionPerformed(evt);
            }
        });

        Cs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesionlogo.jpg"))); // NOI18N
        Cs.setText("Cerrar sesion");
        Cs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(Cs))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(Cs)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mod)
                                .addComponent(add))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("Registro", jScrollPane1);

        jLabel14.setText("Filtrat por:");

        Filtroest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Filtro", "Apellido P", "Apellido M", "Nombre(s)" }));
        Filtroest.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FiltroestItemStateChanged(evt);
            }
        });

        LabelF1.setText("jLabel15");

        Filname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FilnameKeyReleased(evt);
            }
        });

        FilAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FilAPKeyReleased(evt);
            }
        });

        FilAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FilAMKeyReleased(evt);
            }
        });

        Testadia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18", "Title 19", "Title 20", "Title 21", "Title 22"
            }
        ));
        Testadia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TestadiaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Testadia);

        botonWeb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Microsoft-Excel-Logo.png"))); // NOI18N
        botonWeb1.setToolTipText("");
        botonWeb1.setLink("http://192.168.3.10/Reportes/ReporteRH/EPCEstadia.php");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Filtroest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonWeb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelF1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Filname, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilAP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(FilAM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 3644, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Filtroest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelF1)
                    .addComponent(Filname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FilAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FilAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonWeb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel2);

        jTabbedPane1.addTab("Tabla Estadia", jScrollPane2);

        Menuadm.setText("Todas las ventanas");

        jMenu2.setText("Area Nomina");

        jMenu3.setText("Nomina quincenal");

        ODT.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ODT.setText("Ordenes de taller");
        ODT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ODTActionPerformed(evt);
            }
        });
        jMenu3.add(ODT);

        CNQ.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CNQ.setText("Nomina IMSS");
        CNQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CNQActionPerformed(evt);
            }
        });
        jMenu3.add(CNQ);

        PRESQ.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        PRESQ.setText("Prestamos");
        PRESQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRESQActionPerformed(evt);
            }
        });
        jMenu3.add(PRESQ);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem5.setText("Nomina General");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        CDA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CDA.setText("Caja de ahorro");
        CDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDAActionPerformed(evt);
            }
        });
        jMenu3.add(CDA);

        jMenu2.add(jMenu3);

        jMenu4.setText("Semanal");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem6.setText("Nomina Semanal IMSS");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem7.setText("Prestamos Semanales");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem8.setText("Nomina Semanal General");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        CDA4.setText("Caja de ahorro");
        CDA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDA4ActionPerformed(evt);
            }
        });
        jMenu4.add(CDA4);

        ODT2.setText("Ordenes de taller");
        ODT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ODT2ActionPerformed(evt);
            }
        });
        jMenu4.add(ODT2);

        jMenu2.add(jMenu4);

        LDA.setText("Listas de asistencia C/IMSS ");
        LDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LDAActionPerformed(evt);
            }
        });
        jMenu2.add(LDA);

        LDA3.setText("Listas de asistencia S/IMSS");
        LDA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LDA3ActionPerformed(evt);
            }
        });
        jMenu2.add(LDA3);

        Menuadm.add(jMenu2);

        jMenu5.setText("Area RH");

        General1.setText("Empleados General");
        General1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                General1ActionPerformed(evt);
            }
        });
        jMenu5.add(General1);

        Estadias.setText("Alumno de estadia");
        Estadias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadiasActionPerformed(evt);
            }
        });
        jMenu5.add(Estadias);

        Torteria.setText("Empleados Torteria");
        Torteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TorteriaActionPerformed(evt);
            }
        });
        jMenu5.add(Torteria);

        jMenu6.setText("Semanales");

        jMenuItem10.setText("Inturbide");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem11.setText("Tehuantepec");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuItem12.setText("PTE titla");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem21.setText("Generar padron");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem21);

        jMenuItem22.setText("Generar vale de efectivo");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem22);

        jMenu5.add(jMenu6);

        Depositos1.setText("Depositos");

        jMenu8.setText("Quincenales");

        jMenuItem13.setText("Depositos C/ IMSS");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem13);

        jMenuItem14.setText("Depositos S/ IMSS");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem14);

        Depositos1.add(jMenu8);

        jMenu9.setText("Semanales");

        jMenuItem15.setText("Depositos C/ IMSS");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem15);

        jMenuItem16.setText("Depositos S/ IMSS");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem16);

        Depositos1.add(jMenu9);

        jMenu5.add(Depositos1);

        Menuadm.add(jMenu5);

        ZYS.setText("Zonas y Servicios");
        ZYS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZYSActionPerformed(evt);
            }
        });
        Menuadm.add(ZYS);

        ADMV1.setText("Usuarios");
        ADMV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADMV1ActionPerformed(evt);
            }
        });
        Menuadm.add(ADMV1);

        jMenuBar1.add(Menuadm);

        jMenu1.setText("Cambiar a");

        General.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        General.setText("Empleados General");
        General.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeneralActionPerformed(evt);
            }
        });
        jMenu1.add(General);

        EmpleadosT.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        EmpleadosT.setText("Empelados torteria");
        EmpleadosT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpleadosTActionPerformed(evt);
            }
        });
        jMenu1.add(EmpleadosT);

        Depositos.setText("Depositos");

        jMenu7.setText("Quincenales");

        jMenuItem1.setText("Depositos C/ IMSS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem1);

        jMenuItem9.setText("Depositos S/ IMSS");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        jMenuItem24.setText("Santander Depositos C/ IMSS");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem24);

        jMenuItem25.setText("Santander Depositos S/ IMSS");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem25);

        Depositos.add(jMenu7);

        jMenu11.setText("Semanales");

        jMenuItem23.setText("Depositos C/ IMSS");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem23);

        jMenuItem26.setText("Depositos S/ IMSS");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem26);

        jMenuItem27.setText("Santander Depositos C/ IMSS");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem27);

        jMenuItem28.setText("Santander Depositos S/ IMSS");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem28);

        Depositos.add(jMenu11);

        jMenu1.add(Depositos);

        jMenuBar1.add(jMenu1);

        Semanales.setText("Semanales");

        jMenuItem2.setText("Iturbide");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Semanales.add(jMenuItem2);

        jMenuItem3.setText("Tehuantepec");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Semanales.add(jMenuItem3);

        jMenuItem4.setText("PTE titla");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Semanales.add(jMenuItem4);

        jMenuItem19.setText("Generar padron");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        Semanales.add(jMenuItem19);

        jMenuItem20.setText("Generar vale de efectivo");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        Semanales.add(jMenuItem20);

        jMenuBar1.add(Semanales);

        jMenu10.setText("Seguridad.");

        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IcoCDU.png"))); // NOI18N
        jMenuItem17.setText("Cambiar de usuario");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem17);

        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FilnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FilnameKeyReleased
        MDE();
    }//GEN-LAST:event_FilnameKeyReleased

    private void FilAPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FilAPKeyReleased
        MDE();
    }//GEN-LAST:event_FilAPKeyReleased

    private void FilAMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FilAMKeyReleased
        MDE();
    }//GEN-LAST:event_FilAMKeyReleased

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        AgregarE();
        MDE();
        cleanest();
    }//GEN-LAST:event_addActionPerformed

    private void modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modActionPerformed
        EditarEst();
        MDE();
        cleanest();
    }//GEN-LAST:event_modActionPerformed

    private void TestadiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TestadiaMouseClicked
        DefaultTableModel model = (DefaultTableModel) Testadia.getModel();
        try {

            int fila = Testadia.getSelectedRow();
            NexpEst.setText(String.valueOf(Testadia.getValueAt(fila, 0)));
            APEst.setText(String.valueOf(Testadia.getValueAt(fila, 1)));
            AMest.setText(String.valueOf(Testadia.getValueAt(fila, 2)));
            nameest.setText(String.valueOf(Testadia.getValueAt(fila, 3)));
            NCest.setText(String.valueOf(Testadia.getValueAt(fila, 4)));
            Carrest.setText(String.valueOf(Testadia.getValueAt(fila, 5)));
            String combo1 = model.getValueAt(fila, 6).toString();
            for (int i = 0; i < TDCest.getItemCount(); i++) {
                if (TDCest.getItemAt(i).equalsIgnoreCase(combo1)) {
                    TDCest.setSelectedIndex(i);
                }
            }
            Curpest.setText(String.valueOf(Testadia.getValueAt(fila, 7)));
            //Combobo2
            String combo2 = model.getValueAt(fila, 8).toString();
            for (int i = 0; i < Statusest.getItemCount(); i++) {
                if (Statusest.getItemAt(i).equalsIgnoreCase(combo2)) {
                    Statusest.setSelectedIndex(i);
                }
            }
            Obsest.setText(String.valueOf(Testadia.getValueAt(fila, 20)));
            Fdiest.setText(String.valueOf(Testadia.getValueAt(fila, 9)));
            Fdtest.setText(String.valueOf(Testadia.getValueAt(fila, 10)));

            int id = Integer.parseInt(Testadia.getValueAt(fila, 0).toString());
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement("select * from estadia where idEstadias =?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            java.sql.Statement st = con.createStatement();
            while (rs.next()) {
                if (rs.getString("Carta de precentacion").equals("x")) {
                    Cdpest.setSelected(true);

                } else if (rs.getString("Carta de precentacion").equals("")) {
                    Cdpest.setSelected(false);
                }
                if (rs.getString("Carta de aceptacion").equals("x")) {
                    Cdaest.setSelected(true);
                } else if (rs.getString("Carta de aceptacion").equals("")) {
                    Cdaest.setSelected(false);
                }
                if (rs.getString("Programa de actividades").equals("x")) {
                    Pdaest.setSelected(true);
                } else if (rs.getString("Programa de actividades").equals("")) {
                    Pdaest.setSelected(false);
                }
                if (rs.getString("1er avance").equals("x")) {
                    Paest.setSelected(true);
                } else if (rs.getString("1er avance").equals("")) {
                    Paest.setSelected(false);
                }
                if (rs.getString("2do avance").equals("x")) {
                    Saest.setSelected(true);
                } else if (rs.getString("2do avance").equals("")) {
                    Saest.setSelected(false);
                }
                if (rs.getString("3er avance").equals("x")) {
                    Taest.setSelected(true);
                } else if (rs.getString("3er avance").equals("")) {
                    Taest.setSelected(false);
                }
                if (rs.getString("Carta termino").equals("x")) {
                    Ctest.setSelected(true);
                } else if (rs.getString("Carta termino").equals("")) {
                    Ctest.setSelected(false);
                }
                if (rs.getString("Autorizacion empastado").equals("x")) {
                    Adeest.setSelected(true);
                } else if (rs.getString("Autorizacion empastado").equals("")) {
                    Adeest.setSelected(false);
                }
                if (rs.getString("Evaluacion").equals("x")) {
                    Evest.setSelected(true);
                } else if (rs.getString("Evaluacion").equals("")) {
                    Evest.setSelected(false);

                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());

        }

    }//GEN-LAST:event_TestadiaMouseClicked

    private void FiltroestItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FiltroestItemStateChanged
        String dt = (String) Filtroest.getSelectedItem();
        if (dt.equals("Selecciona Filtro")) {
            LabelF1.setText("");
            LabelF1.setVisible(false);
            FilAP.setText("");
            FilAP.setVisible(false);
            FilAM.setVisible(false);
            FilAM.setText("");
            Filname.setVisible(false);
            Filname.setText("");
            MDE();
        }
        if (dt.equals("Apellido P")) {
            LabelF1.setVisible(true);
            LabelF1.setText("Buscar Apellido P:");
            FilAP.setText("");
            FilAP.setVisible(true);
            FilAM.setVisible(false);
            FilAM.setText("");
            Filname.setVisible(false);
            Filname.setText("");
            MDE();
        }
        if (dt.equals("Apellido M")) {
            LabelF1.setVisible(true);
            LabelF1.setText("Buscar Apellido M:");
            FilAP.setText("");
            FilAP.setVisible(false);
            FilAM.setVisible(true);
            FilAM.setText("");
            Filname.setVisible(false);
            Filname.setText("");
            MDE();
        }
        if (dt.equals("Nombre(s)")) {
            LabelF1.setVisible(true);
            LabelF1.setText("Buscar Nombre(s):");
            FilAP.setText("");
            FilAP.setVisible(false);
            FilAM.setVisible(false);
            FilAM.setText("");
            Filname.setVisible(true);
            Filname.setText("");
            MDE();
        }
    }//GEN-LAST:event_FiltroestItemStateChanged

    private void GeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeneralActionPerformed
        Empleados_4 regr = new Empleados_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_GeneralActionPerformed

    private void CsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CsActionPerformed

        int i = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres cerrar la sesion?");
        if (i == 0) {
            Inicio_1 regr = new Inicio_1();
            regr.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_CsActionPerformed

    private void EmpleadosTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpleadosTActionPerformed

        Tortas_4 regr = new Tortas_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_EmpleadosTActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Iturbide_4 regr = new Iturbide_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Tehuantepec_4 regr = new Tehuantepec_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        PT_4 regr = new PT_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void ODTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ODTActionPerformed
        ODTQ_5 regr = new ODTQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ODTActionPerformed

    private void CNQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CNQActionPerformed
        NominaQ_5 regr = new NominaQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CNQActionPerformed

    private void PRESQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRESQActionPerformed
        PresQ_5 regr = new PresQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_PRESQActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        NominaQSiMSS_5 regr = new NominaQSiMSS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void CDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDAActionPerformed
        CDAQ_5 regr = new CDAQ_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CDAActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        NominaS_5 regr = new NominaS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        PresS_5 regr = new PresS_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        NominaS_simss_5 regr = new NominaS_simss_5(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

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

    private void General1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_General1ActionPerformed

        Empleados_4 RH = new Empleados_4(usr, LP);
        RH.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_General1ActionPerformed

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

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Iturbide_4 regr = new Iturbide_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        Tehuantepec_4 regr = new Tehuantepec_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        PT_4 regr = new PT_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void ZYSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZYSActionPerformed
        AltasZyS_3 regr = new AltasZyS_3(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ZYSActionPerformed

    private void ADMV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADMV1ActionPerformed
        VentanaADM_3 regr = new VentanaADM_3(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ADMV1ActionPerformed

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

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        DepositosQ_4 regr = new DepositosQ_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        DepositosQ_SIMSS_4 regr = new DepositosQ_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        DepositosS_4 regr = new DepositosS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        DepositosS_SIMSS_4 regr = new DepositosS_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        Padrones regr = new Padrones(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        VDE regr = new VDE(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        Padrones regr = new Padrones(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        VDE regr = new VDE(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DepositosQ_4 regr = new DepositosQ_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        DepositosQ_SIMSS_4 regr = new DepositosQ_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

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

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        DepositosS_SIMSS_4 regr = new DepositosS_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        DepositosSSan_4 regr = new DepositosSSan_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        DepositosSSan_SIMSS_4 regr = new DepositosSSan_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed

        int i = JOptionPane.showConfirmDialog(this, "El cambiar de usuario cerrara la ventana actual. \n ¿Seguir con esta accion?");
        if (i == 0) {
            Inicio_1 regr = new Inicio_1();
            regr.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Estadias_4.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estadias_4.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estadias_4.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estadias_4.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estadias_4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ADMV1;
    private javax.swing.JTextField AMest;
    private javax.swing.JTextField APEst;
    private javax.swing.JCheckBox Adeest;
    private javax.swing.JMenuItem CDA;
    private javax.swing.JMenuItem CDA4;
    private javax.swing.JMenuItem CNQ;
    private javax.swing.JTextField Carrest;
    private javax.swing.JCheckBox Cdaest;
    private javax.swing.JCheckBox Cdpest;
    private javax.swing.JButton Cs;
    private javax.swing.JCheckBox Ctest;
    private javax.swing.JTextField Curpest;
    private javax.swing.JMenu Depositos;
    private javax.swing.JMenu Depositos1;
    private javax.swing.JMenuItem EmpleadosT;
    private javax.swing.JMenuItem Estadias;
    private javax.swing.JCheckBox Evest;
    private javax.swing.JTextField Fdiest;
    private javax.swing.JTextField Fdtest;
    private javax.swing.JTextField FilAM;
    private javax.swing.JTextField FilAP;
    private javax.swing.JTextField Filname;
    private javax.swing.JComboBox<String> Filtroest;
    private javax.swing.JMenuItem General;
    private javax.swing.JMenuItem General1;
    private javax.swing.JMenuItem LDA;
    private javax.swing.JMenuItem LDA3;
    private javax.swing.JLabel LabelF1;
    private javax.swing.JMenu Menuadm;
    private javax.swing.JTextField NCest;
    private javax.swing.JTextField NexpEst;
    private javax.swing.JMenuItem ODT;
    private javax.swing.JMenuItem ODT2;
    private javax.swing.JTextArea Obsest;
    private javax.swing.JMenuItem PRESQ;
    private javax.swing.JCheckBox Paest;
    private javax.swing.JCheckBox Pdaest;
    private javax.swing.JCheckBox Saest;
    private javax.swing.JMenu Semanales;
    private javax.swing.JComboBox<String> Statusest;
    private javax.swing.JComboBox<String> TDCest;
    private javax.swing.JCheckBox Taest;
    private javax.swing.JTable Testadia;
    private javax.swing.JMenuItem Torteria;
    private javax.swing.JMenuItem ZYS;
    private javax.swing.JButton add;
    private botones.BotonWeb botonWeb1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
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
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton mod;
    private javax.swing.JTextField nameest;
    // End of variables declaration//GEN-END:variables
}
