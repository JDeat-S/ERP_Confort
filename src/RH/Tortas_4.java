package RH;

import RH.Depositos.DepositosQ_4;
import RH.Depositos.DepositosQ_SIMSS_4;
import RH.Depositos.DepositosS_4;
import RH.Depositos.DepositosS_SIMSS_4;
import RH.Depositos.Santander.DepositosQSan_4;
import RH.Depositos.Santander.DepositosQsan_SIMSS_4;
import RH.Depositos.Santander.DepositosSSan_4;
import RH.Depositos.Santander.DepositosSSan_SIMSS_4;
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
import Admin.*;
import Nomina.*;
import Conexion.ConexionSQL;
import Inicio.Inicio_1;
import Logicas.Logica_permisos;
import Logicas.Logica_usuarios;
import Semanal.Padrones.Padrones;
import Semanal.Vales.Rvales;
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

public final class Tortas_4 extends javax.swing.JFrame {

    ConexionSQL cc = new ConexionSQL();
    Connection con = cc.conexion();
    Logica_usuarios usr;
    Logica_permisos LP;

    public Tortas_4() {
        initComponents();
        LabelF1.setVisible(false);
        filap.setVisible(false);
        filam.setVisible(false);
        Filname.setVisible(false);
        this.setExtendedState(6);
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Tortas_4.class.getClassLoader().getResource("Imagenes/Icono.png")).getImage());
        MDT();

    }

    public Tortas_4(Logica_usuarios usr, Logica_permisos LP) {
        initComponents();
        this.usr = usr;
        this.LP = LP;
        LabelF1.setVisible(false);
        filap.setVisible(false);
        filam.setVisible(false);
        Filname.setVisible(false);
        this.setExtendedState(6);
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Tortas_4.class.getClassLoader().getResource("Imagenes/Icono.png")).getImage());
        MDT();
        setIconImage(new ImageIcon(Estadias_4.class.getClassLoader().getResource("Imagenes/Icono.png")).getImage());
        setTitle("Empleados de torteria # Usuario: " + usr.getId_user() + " " + usr.getApellidop() + " " + usr.getApellidoM() + " " + usr.getNombre()
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
                }
                if (LP.getP3() == 0) {
                    Depositos.setVisible(false);
                }
                if (LP.getP4() == 0) {
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
                }
                if (LP.getP3() == 0) {
                    Depositos.setVisible(false);
                }
                if (LP.getP4() == 0) {
                    Semanales.setVisible(false);
                }
            }
            default -> {
            }
        }
    }

    public void clean() {
        NDEtor.setText("0");
        Aptor.setText("");
        amtor.setText("");
        nametor.setText("");
        Ndctor.setText("");
        Ndrtor.setText("");
        Ndcel.setText("");
        RFCtor.setText("");
        nsstor.setText("");
        curptor.setText("");
        Dotor.setText("");
        Dftor.setText("");
        Detor.setText("");
        Fdptor.setText("");
        sueltor.setText("");
        Bonotor.setText("");
        Statustor.setSelectedIndex(0);
        Fetor.setText("");
        FItor.setText("");
        UDLtor.setText("");
        FdFBtor.setText("");
        BFtor.setSelectedIndex(0);
        Fintor.setSelectedIndex(0);
        FREI.setText("");
        UDLRE.setText("");
        FFBRE.setText("");
        FBRE.setText("");
        Fintor.setSelectedIndex(0);
        CdStor.setText("");
        Nrptor.setText("");
        obstor.setText("");
    }

    public void AgregarE() {
        String Status = Statustor.getSelectedItem().toString();
        String BF = BFtor.getSelectedItem().toString();
        String Finiquito = Fintor.getSelectedItem().toString();
        String BFREtor = BFRE.getSelectedItem().toString();

        String SQL = "INSERT INTO `confort`.`torteria` (`# Exp`, `Apellido P`, `Apellido M`,"
                + " `Nombre(s)`, `# Casa`, `# Recados`, `# Celular`, `RFC`, `NSS`, `CURP`,"
                + " `Doc originales`, `Doc faltantes`, `Doc entregados`, `Forma de pago`,"
                + " `Sueldo`, `Bono`, `Status`, `Fecha entrevista`, `Fecha ingreso`,"
                + " `Ultimo dia laborado`, `Fecha de firma baja`, `Baja firmada`, `Finiquito`,"
                + " `Fecha de Re-ingreso`, `Ultimo dia laborado (re)`, `Fecha firma baja (re)`,"
                + " `Fecha de Baja (re)`, `Baja firmada (re)`, `Cambio de Servicio`, `# recepcion personal`,"
                + " `Observaciones`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(SQL);

            pst.setInt(1, Integer.parseInt(NDEtor.getText()));
            pst.setString(2, Aptor.getText());
            pst.setString(3, amtor.getText());
            pst.setString(4, nametor.getText());
            pst.setString(5, Ndctor.getText());
            pst.setString(6, Ndrtor.getText());
            pst.setString(7, Ndcel.getText());
            pst.setString(8, RFCtor.getText());
            pst.setString(9, nsstor.getText());
            pst.setString(10, curptor.getText());
            pst.setString(11, Dotor.getText());
            pst.setString(12, Dftor.getText());
            pst.setString(13, Detor.getText());
            pst.setString(14, Fdptor.getText());
            pst.setString(15, sueltor.getText());
            pst.setString(16, Bonotor.getText());
            pst.setString(17, Status);
            pst.setString(18, Fetor.getText());
            pst.setString(19, FItor.getText());
            pst.setString(20, UDLtor.getText());
            pst.setString(21, FdFBtor.getText());
            pst.setString(22, BF);
            pst.setString(23, Finiquito);
            pst.setString(24, FREI.getText());
            pst.setString(25, UDLRE.getText());
            pst.setString(26, FFBRE.getText());
            pst.setString(27, FBRE.getText());
            pst.setString(28, BFREtor);
            pst.setString(29, CdStor.getText());
            pst.setString(30, Nrptor.getText());
            pst.setString(31, obstor.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Empleado de torteria agregado.");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar en Empleado de torteria: " + e.getMessage());
        }
    }

    //Mostrar datostorteria
    public void MDT() {

        String FiltroNGe = Filname.getText();
        String FAP = filap.getText();
        String FAM = filam.getText();
        String where = "select * from torteria";
        if (!"".equals(FiltroNGe)) {
            where = "Select * from torteria where `Nombre(s)` LIKE '%" + FiltroNGe + "%'";
        } else if (!"".equals(FAP)) {
            where = "Select * from torteria where `Apellido P` LIKE '%" + FAP + "%'";
        } else if (!"".equals(FAM)) {
            where = "Select * from torteria where `Apellido M` LIKE '%" + FAM + "%'";
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
            TTortas.setModel(modelo);
            PreparedStatement ps;
            ResultSet rs;

            ps = con.prepareStatement(where);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID BD");//1
            modelo.addColumn("# Exp");
            modelo.addColumn("Apellido P");//3
            modelo.addColumn("Apellido M");
            modelo.addColumn("Nombre(s)");//5
            modelo.addColumn("# Casa");
            modelo.addColumn("# Recados");//7
            modelo.addColumn("# Celular");
            modelo.addColumn("RFC");//9
            modelo.addColumn("NSS");
            modelo.addColumn("CURP");//11
            modelo.addColumn("Doc. Originales");
            modelo.addColumn("Doc. Faltantes");//13
            modelo.addColumn("Doc. Entregables");
            modelo.addColumn("Forma de pago");//15
            modelo.addColumn("Sueldo");
            modelo.addColumn("Bono");//17
            modelo.addColumn("Status");
            modelo.addColumn("Fecha entrevista");//19
            modelo.addColumn("Fecha ingreso");
            modelo.addColumn("Fecha ultimo dia laborado");//21
            modelo.addColumn("Fecha firma baja");
            modelo.addColumn("Baja firmada");//23
            modelo.addColumn("Finiquito");
            modelo.addColumn("Cambio de servicio");//25
            modelo.addColumn("Fecha de Re-ingreso");
            modelo.addColumn("Fecha ultimo dia laborado (Re)");
            modelo.addColumn("Fecha firma baja (Re)");//28
            modelo.addColumn("Fecha de baja (Re)");
            modelo.addColumn("Baja firmada (Re)");//30
            modelo.addColumn("# recepcion personal");
            modelo.addColumn("Observaciones");//32

            int[] anchos = {/*idbd*/35, /*Exp*/ 50, /*ap*/ 70, /*am*/ 70, /*name*/ 100, /*casa*/ 65, /*recados*/ 70, /*celular*/ 65, /*rfc*/ 60,
                /*nss*/ 65, /*curp*/ 70, /*DO*/ 500, /*DF*/ 300, /*DE*/ 300, /*fdp*/ 70, /*sueldo*/ 40, /*bono*/ 35, /*status*/ 75,
                /*FDE*/ 75, /*FDI*/ 75, /*FUDL*/ 75, /*FFB*/ 75, /*BF*/ 60, /*FIN*/ 70, /*CS*/ 75, /*FRE*/ 85, /*FUDLRE*/ 75,
                /*FFBRE*/ 75, /*FDBRE*/ 75, /*FBRE*/ 60, /*NRP*/ 60, /*OBS*/ 2000};

            for (int x = 0; x < cantidadColumnas; x++) {
                //Nombre tabla
                TTortas.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);

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

    public void editar() {
        String Status = Statustor.getSelectedItem().toString();
        String BF = BFtor.getSelectedItem().toString();
        String Finiquito = Fintor.getSelectedItem().toString();
        String BFREtor = BFRE.getSelectedItem().toString();
        String SQL = "UPDATE `confort`.`torteria` SET `# Exp` = ?, `Apellido P` = ?,"
                + " `Apellido M` = ?, `Nombre(s)` = ?, `# Casa` = ?, `# Recados` = ?,"
                + " `# Celular` = ?, `RFC` = ?, `NSS` = ?, `CURP` = ?, `Doc originales` = ?,"
                + " `Doc faltantes` = ?, `Doc entregados` = ?, `Forma de pago` = ?, `Sueldo` = ?,"
                + " `Bono` = ?, `Status` = ?, `Fecha entrevista` = ?, `Fecha ingreso` = ?,"
                + " `Ultimo dia laborado` = ?, `Fecha de firma baja` = ?, `Baja firmada` = ?,"
                + " `Finiquito` = ?, `Fecha de Re-ingreso` = ?, `Ultimo dia laborado (re)` = ?,"
                + " `Fecha firma baja (re)` = ?, `Fecha de Baja (re)` = ?, `Baja firmada (re)` = ?,"
                + " `Cambio de Servicio` = ?, `# recepcion personal` = ?, `Observaciones` = ? WHERE `# Exp` = ?";

        try {
            PreparedStatement pst = con.prepareStatement(SQL);

            pst.setInt(1, Integer.parseInt(NDEtor.getText()));
            pst.setString(2, Aptor.getText());
            pst.setString(3, amtor.getText());
            pst.setString(4, nametor.getText());
            pst.setString(5, Ndctor.getText());
            pst.setString(6, Ndrtor.getText());
            pst.setString(7, Ndcel.getText());
            pst.setString(8, RFCtor.getText());
            pst.setString(9, nsstor.getText());
            pst.setString(10, curptor.getText());
            pst.setString(11, Dotor.getText());
            pst.setString(12, Dftor.getText());
            pst.setString(13, Detor.getText());
            pst.setString(14, Fdptor.getText());
            pst.setString(15, sueltor.getText());
            pst.setString(16, Bonotor.getText());
            pst.setString(17, Status);
            pst.setString(18, Fetor.getText());
            pst.setString(19, FItor.getText());
            pst.setString(20, UDLtor.getText());
            pst.setString(21, FdFBtor.getText());
            pst.setString(22, BF);
            pst.setString(23, Finiquito);
            pst.setString(24, FREI.getText());
            pst.setString(25, UDLRE.getText());
            pst.setString(26, FFBRE.getText());
            pst.setString(27, FBRE.getText());
            pst.setString(28, BFREtor);
            pst.setString(29, CdStor.getText());
            pst.setString(30, Nrptor.getText());
            pst.setString(31, obstor.getText());
            pst.setInt(32, Integer.parseInt(NDEtor.getText()));

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Empleado Modificado");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en modificar empleado: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NDEtor = new javax.swing.JTextField();
        RFCtor = new javax.swing.JTextField();
        Aptor = new javax.swing.JTextField();
        nsstor = new javax.swing.JTextField();
        amtor = new javax.swing.JTextField();
        curptor = new javax.swing.JTextField();
        nametor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Ndctor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Ndrtor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Ndcel = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Dotor = new javax.swing.JTextField();
        Dftor = new javax.swing.JTextField();
        Detor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Fe = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Fdptor = new javax.swing.JTextField();
        sueltor = new javax.swing.JTextField();
        Bonotor = new javax.swing.JTextField();
        Statustor = new javax.swing.JComboBox<>();
        Fetor = new javax.swing.JTextField();
        FItor = new javax.swing.JTextField();
        UDLtor = new javax.swing.JTextField();
        FdFBtor = new javax.swing.JTextField();
        BFtor = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        Fintor = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        BFRE = new javax.swing.JComboBox<>();
        FREI = new javax.swing.JTextField();
        UDLRE = new javax.swing.JTextField();
        FBRE = new javax.swing.JTextField();
        FFBRE = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        CdStor = new javax.swing.JTextField();
        Nrptor = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        obstor = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Cs = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Filtrotor = new javax.swing.JComboBox<>();
        LabelF1 = new javax.swing.JLabel();
        Filname = new javax.swing.JTextField();
        filap = new javax.swing.JTextField();
        filam = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        TTortas = new javax.swing.JTable();
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
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
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
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        Semanales = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Nombre(s):");

        jLabel8.setText("RFC:");

        jLabel5.setText("# Casa");

        jLabel9.setText("NSS");

        jLabel6.setText("# Recados");

        jLabel10.setText("CURP:");

        jLabel7.setText("# Celular:");

        NDEtor.setText("0");

        jLabel1.setText("# Exp");

        jLabel2.setText("Apellido P:");

        jLabel3.setText("Apellido M:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ndrtor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ndcel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NDEtor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Aptor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amtor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nametor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ndctor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RFCtor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nsstor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(curptor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NDEtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Aptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(amtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nametor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Ndctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Ndrtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Ndcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(RFCtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(nsstor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(curptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel11.setText("Documentos");

        jLabel12.setText("Doc. originales:");

        jLabel13.setText("Doc. faltantes:");

        jLabel14.setText("Doc. entregados:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(118, 118, 118))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Dftor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Detor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dotor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(Dotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Dftor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Detor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setText("Forma de pago:");

        jLabel16.setText("Sueldo:");

        jLabel17.setText("Bono:");

        jLabel18.setText("Status:");

        Fe.setText("Fecha entrevista:");

        jLabel20.setText("Fecha ingreso:");

        jLabel21.setText("Ultimo Dia laborado");

        jLabel22.setText("Fecha de firma baja");

        jLabel23.setText("Baja firmada:");

        Statustor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "VIGENTE", "BAJA", "RECHAZADO", "IMSS", "EN ESPERA", "PENDIENTES", " " }));

        BFtor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "Si", "No", "No aplica", "No ha venido a firmar", "Firma no coincide con la ine", "Pendiente" }));

        jLabel24.setText("Finiquito:");

        Fintor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "No", "No aplica", "Si", "Si se le dio", "Pagado", "Pendiente" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(Fe)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fdptor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sueltor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bonotor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Statustor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fetor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FItor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UDLtor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FdFBtor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BFtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fintor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(Fdptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(sueltor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(Bonotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(Statustor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fe)
                    .addComponent(Fetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(FItor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(UDLtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(FdFBtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(BFtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(Fintor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel31.setText("Re-ingreso");

        jLabel35.setText("Fecha de Re-ingreso:");

        jLabel36.setText("Ultimo dia laborado:");

        jLabel37.setText("Fecha de Baja:");

        jLabel38.setText("Baja firmada:");

        BFRE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "Si", "No", "No aplica", "No ha venido a firmar", "Firma no coincide con la ine", "Pendiente" }));

        FFBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FFBREActionPerformed(evt);
            }
        });

        jLabel43.setText("Fecha firma baja:");

        jLabel25.setText("Cambio de Servicio:");

        jLabel26.setText("# recepcion personal:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BFRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FREI, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(FFBRE, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(UDLRE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                            .addComponent(Nrptor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CdStor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel31)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(FREI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(UDLRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FFBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(FBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(BFRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(CdStor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(Nrptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel27.setText("Observaciones:");

        obstor.setColumns(20);
        obstor.setRows(5);
        jScrollPane3.setViewportView(obstor);

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(Cs)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cs))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("Registro", jScrollPane1);

        jLabel19.setText("Filtrar por:");

        Filtrotor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Filtro", "Apellido P", "Apellido M", "Nombre(s)" }));
        Filtrotor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FiltrotorItemStateChanged(evt);
            }
        });

        LabelF1.setText("jLabel28");

        Filname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FilnameKeyReleased(evt);
            }
        });

        filap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filapKeyReleased(evt);
            }
        });

        filam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filamKeyReleased(evt);
            }
        });

        TTortas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18", "Title 19", "Title 20", "Title 21", "Title 22", "Title 23", "Title 24", "Title 25", "Title 26", "Title 27", "Title 28", "Title 29", "Title 30", "Title 31", "Title 32"
            }
        ));
        TTortas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TTortasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TTortas);

        botonWeb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Microsoft-Excel-Logo.png"))); // NOI18N
        botonWeb1.setLink("http://192.168.3.10/Reportes/ReporteRH/EPCTorteria.php");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Filtrotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonWeb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelF1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Filname, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 6806, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(Filtrotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelF1)
                    .addComponent(Filname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonWeb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jTabbedPane1.addTab("Tabla torteria", jScrollPane2);

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

        jMenuItem10.setText("Iturbide");
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

        jMenuItem22.setText("Reimprimir vale");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem22);

        jMenuItem23.setText("Generar vale de efectivo");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem23);

        jMenuItem17.setText("Generar padron");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem17);

        jMenu5.add(jMenu6);

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

        jMenuItem26.setText("Santander Depositos S/ IMSS");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem26);

        Depositos.add(jMenu7);

        jMenu8.setText("Semanales");

        jMenuItem15.setText("Depositos C/ IMSS");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenuItem16.setText("Depositos S/ IMSS");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenuItem27.setText("Santander Depositos C/ IMSS");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem27);

        jMenuItem28.setText("Santander Depositos S/ IMSS");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem28);

        Depositos.add(jMenu8);

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

        jMenuItem25.setText("Generar vale de efectivo");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        Semanales.add(jMenuItem25);

        jMenuItem18.setText("Generar padron");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        Semanales.add(jMenuItem18);

        jMenuBar1.add(Semanales);

        jMenu9.setText("Seguridad.");

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IcoCDU.png"))); // NOI18N
        jMenuItem13.setText("Cambiar de usuario");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem13);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1117, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FFBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FFBREActionPerformed
    }//GEN-LAST:event_FFBREActionPerformed

    private void FiltrotorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FiltrotorItemStateChanged
        String dt = (String) Filtrotor.getSelectedItem();
        if (dt.equals("Selecciona Filtro")) {
            LabelF1.setText("");
            LabelF1.setVisible(false);
            filap.setText("");
            filap.setVisible(false);
            filam.setVisible(false);
            filam.setText("");
            Filname.setVisible(false);
            Filname.setText("");
            MDT();
        }
        if (dt.equals("Apellido P")) {
            LabelF1.setVisible(true);
            LabelF1.setText("Buscar Apellido P:");
            filap.setText("");
            filap.setVisible(true);
            filam.setVisible(false);
            filam.setText("");
            Filname.setVisible(false);
            Filname.setText("");
            MDT();
        }
        if (dt.equals("Apellido M")) {
            LabelF1.setVisible(true);
            LabelF1.setText("Buscar Apellido M:");
            filap.setText("");
            filap.setVisible(false);
            filam.setVisible(true);
            filam.setText("");
            Filname.setVisible(false);
            Filname.setText("");
            MDT();
        }
        if (dt.equals("Nombre(s)")) {
            LabelF1.setVisible(true);
            LabelF1.setText("Buscar Nombre(s):");
            filap.setText("");
            filap.setVisible(false);
            filam.setVisible(false);
            filam.setText("");
            Filname.setVisible(true);
            Filname.setText("");
            MDT();
        }
    }//GEN-LAST:event_FiltrotorItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AgregarE();
        MDT();
        clean();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void FilnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FilnameKeyReleased
        MDT();

    }//GEN-LAST:event_FilnameKeyReleased

    private void filapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filapKeyReleased
        MDT();

    }//GEN-LAST:event_filapKeyReleased

    private void filamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filamKeyReleased
        MDT();

    }//GEN-LAST:event_filamKeyReleased

    private void TTortasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TTortasMouseClicked
        DefaultTableModel model = (DefaultTableModel) TTortas.getModel();

        int fila = TTortas.getSelectedRow();
        NDEtor.setText(String.valueOf(TTortas.getValueAt(fila, 0)));
        Aptor.setText(String.valueOf(TTortas.getValueAt(fila, 1)));
        amtor.setText(String.valueOf(TTortas.getValueAt(fila, 2)));
        nametor.setText(String.valueOf(TTortas.getValueAt(fila, 3)));
        Ndctor.setText(String.valueOf(TTortas.getValueAt(fila, 4)));
        Ndrtor.setText(String.valueOf(TTortas.getValueAt(fila, 5)));
        Ndcel.setText(String.valueOf(TTortas.getValueAt(fila, 6)));
        RFCtor.setText(String.valueOf(TTortas.getValueAt(fila, 7)));
        nsstor.setText(String.valueOf(TTortas.getValueAt(fila, 8)));
        curptor.setText(String.valueOf(TTortas.getValueAt(fila, 9)));
        Dotor.setText(String.valueOf(TTortas.getValueAt(fila, 10)));
        Dftor.setText(String.valueOf(TTortas.getValueAt(fila, 11)));
        Detor.setText(String.valueOf(TTortas.getValueAt(fila, 12)));
        Fdptor.setText(String.valueOf(TTortas.getValueAt(fila, 13)));
        sueltor.setText(String.valueOf(TTortas.getValueAt(fila, 14)));
        Bonotor.setText(String.valueOf(TTortas.getValueAt(fila, 15)));
        String combo1 = model.getValueAt(fila, 16).toString();
        for (int i = 0; i < Statustor.getItemCount(); i++) {
            if (Statustor.getItemAt(i).equalsIgnoreCase(combo1)) {
                Statustor.setSelectedIndex(i);
            }
        }
        Fetor.setText(String.valueOf(TTortas.getValueAt(fila, 17)));
        FItor.setText(String.valueOf(TTortas.getValueAt(fila, 18)));
        UDLtor.setText(String.valueOf(TTortas.getValueAt(fila, 19)));
        FdFBtor.setText(String.valueOf(TTortas.getValueAt(fila, 20)));
        String combo2 = model.getValueAt(fila, 21).toString();
        for (int i = 0; i < BFtor.getItemCount(); i++) {
            if (BFtor.getItemAt(i).equalsIgnoreCase(combo2)) {
                BFtor.setSelectedIndex(i);
            }
        }
        String combo3 = model.getValueAt(fila, 22).toString();
        for (int i = 0; i < Fintor.getItemCount(); i++) {
            if (Fintor.getItemAt(i).equalsIgnoreCase(combo3)) {
                Fintor.setSelectedIndex(i);
            }
        }
        FREI.setText(String.valueOf(TTortas.getValueAt(fila, 23)));
        UDLRE.setText(String.valueOf(TTortas.getValueAt(fila, 24)));
        FFBRE.setText(String.valueOf(TTortas.getValueAt(fila, 25)));
        FBRE.setText(String.valueOf(TTortas.getValueAt(fila, 26)));
        String combo4 = model.getValueAt(fila, 27).toString();
        for (int i = 0; i < Fintor.getItemCount(); i++) {
            if (Fintor.getItemAt(i).equalsIgnoreCase(combo4)) {
                Fintor.setSelectedIndex(i);
            }
        }
        CdStor.setText(String.valueOf(TTortas.getValueAt(fila, 28)));
        Nrptor.setText(String.valueOf(TTortas.getValueAt(fila, 29)));
        obstor.setText(String.valueOf(TTortas.getValueAt(fila, 30)));

    }//GEN-LAST:event_TTortasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        editar();
        MDT();
        clean();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CsActionPerformed

        int i = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres cerrar la sesion?");
        if (i == 0) {
            Inicio_1 regr = new Inicio_1();
            regr.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_CsActionPerformed

    private void GeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeneralActionPerformed
        Empleados_4 regr = new Empleados_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_GeneralActionPerformed

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

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        Rvales regr = new Rvales(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        VDE regr = new VDE(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        Padrones regr = new Padrones(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        VDE regr = new VDE(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        Padrones regr = new Padrones(usr, LP);
        regr.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

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

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        DepositosQsan_SIMSS_4 regr = new DepositosQsan_SIMSS_4(usr, LP);
        regr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

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

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed

        int i = JOptionPane.showConfirmDialog(this, "El cambiar de usuario cerrara la ventana actual. \n ¿Seguir con esta accion?");
        if (i == 0) {
            Inicio_1 regr = new Inicio_1();
            regr.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

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
            java.util.logging.Logger.getLogger(Tortas_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tortas_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tortas_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tortas_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(() -> {
            new Tortas_4().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ADMV1;
    private javax.swing.JTextField Aptor;
    private javax.swing.JComboBox<String> BFRE;
    private javax.swing.JComboBox<String> BFtor;
    private javax.swing.JTextField Bonotor;
    private javax.swing.JMenuItem CDA;
    private javax.swing.JMenuItem CDA4;
    private javax.swing.JMenuItem CNQ;
    private javax.swing.JTextField CdStor;
    private javax.swing.JButton Cs;
    private javax.swing.JMenu Depositos;
    private javax.swing.JTextField Detor;
    private javax.swing.JTextField Dftor;
    private javax.swing.JTextField Dotor;
    private javax.swing.JMenuItem EmpleadosT;
    private javax.swing.JMenuItem Estadias;
    private javax.swing.JTextField FBRE;
    private javax.swing.JTextField FFBRE;
    private javax.swing.JTextField FItor;
    private javax.swing.JTextField FREI;
    private javax.swing.JTextField FdFBtor;
    private javax.swing.JTextField Fdptor;
    private javax.swing.JLabel Fe;
    private javax.swing.JTextField Fetor;
    private javax.swing.JTextField Filname;
    private javax.swing.JComboBox<String> Filtrotor;
    private javax.swing.JComboBox<String> Fintor;
    private javax.swing.JMenuItem General;
    private javax.swing.JMenuItem General1;
    private javax.swing.JMenuItem LDA;
    private javax.swing.JMenuItem LDA3;
    private javax.swing.JLabel LabelF1;
    private javax.swing.JMenu Menuadm;
    private javax.swing.JTextField NDEtor;
    private javax.swing.JTextField Ndcel;
    private javax.swing.JTextField Ndctor;
    private javax.swing.JTextField Ndrtor;
    private javax.swing.JTextField Nrptor;
    private javax.swing.JMenuItem ODT;
    private javax.swing.JMenuItem ODT2;
    private javax.swing.JMenuItem PRESQ;
    private javax.swing.JTextField RFCtor;
    private javax.swing.JMenu Semanales;
    private javax.swing.JComboBox<String> Statustor;
    private javax.swing.JTable TTortas;
    private javax.swing.JMenuItem Torteria;
    private javax.swing.JTextField UDLRE;
    private javax.swing.JTextField UDLtor;
    private javax.swing.JMenuItem ZYS;
    private javax.swing.JTextField amtor;
    private botones.BotonWeb botonWeb1;
    private javax.swing.JTextField curptor;
    private javax.swing.JTextField filam;
    private javax.swing.JTextField filap;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
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
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nametor;
    private javax.swing.JTextField nsstor;
    private javax.swing.JTextArea obstor;
    private javax.swing.JTextField sueltor;
    // End of variables declaration//GEN-END:variables
}
