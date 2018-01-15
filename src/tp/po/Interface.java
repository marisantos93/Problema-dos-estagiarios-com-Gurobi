package tp.po;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mapda
 */
public class Interface extends javax.swing.JFrame {

 
    public Interface() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    public void TPPL ( int rest1, int rest2, int rest3, int rest4, int rest5,
                       int rest6,int rest7, int rest8, int rest9){

    try{
			int rest10=0;
                        
			GRBEnv ambiente = new GRBEnv("estagio.log"); //novo ambiente
			GRBModel model = new GRBModel(ambiente); //novo modelo
			
			//Declaração de variável
                        //xi: número de estudantes que deverão ser contratados para cada hora
			
			GRBVar x1 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x1");
                        GRBVar x2 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x2");
                        GRBVar x3 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x3");
                        GRBVar x4 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x4");
                        GRBVar x5 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x5");
                        GRBVar x6 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x6");
                        GRBVar x7 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x7");
                        GRBVar x8 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x8");
                        GRBVar x9 = model.addVar(0.0, GRB.INFINITY, 1.0, GRB.INTEGER, "x9");
                        
                    
                        //Função Objetiva
                        //MIN número de estudantes contratados no período i
                        //Somatório de xi para todo i={1,..,9}
                        
                        GRBLinExpr func = new GRBLinExpr(); //nova função linear

                            
                            model.set(GRB.IntAttr.ModelSense, GRB.MINIMIZE);
                        
                        //Restrições
                        
                        //x1 >= 2
                        GRBLinExpr expr = new GRBLinExpr();
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x1); 
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest1, "rt1");
                        
                        // x1 + x2 >= 2
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x1);
                        expr.addTerm(1.0, x2);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest2, "rt2");
                        
                        //x1 + x2 + x3 >= 3
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x1);
                        expr.addTerm(1.0, x2);
                        expr.addTerm(1.0, x3);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest3, "rt3");
                        
                        //x2 + x3 + x4 >= 3
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x2);
                        expr.addTerm(1.0, x3);
                        expr.addTerm(0.0, x4);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest4, "rt4");
                                             
                        //x3 + x4 + x5 >= 3
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x3);
                        expr.addTerm(0.0, x4);
                        expr.addTerm(1.0, x5);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest5, "rt5");
                        
                        //x4 = 0
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x4);
                        model.addConstr(expr, GRB.EQUAL, rest6, "rt6");
                        
                        // x4 + x5 + x6 >= 3
                        expr = new GRBLinExpr();
                        expr.addTerm(0.0, x4);
                        expr.addTerm(1.0, x5);
                        expr.addTerm(1.0, x6);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest7, "rt7");
                       
                        
                        //x5 + x6 + x7 >= 3
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x5);
                        expr.addTerm(1.0, x6);
                        expr.addTerm(1.0, x7);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest8, "rt8");
                        
                        //x6 + x7 + x8 >=3
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x6);
                        expr.addTerm(1.0, x7);
                        expr.addTerm(1.0, x8);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest9, "rt9");
                        
                        //x7 + x8 + x9 >= 3
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x7);
                        expr.addTerm(1.0, x8);
                        expr.addTerm(1.0, x9);
                        model.addConstr(expr, GRB.GREATER_EQUAL, rest10, "rt10");
                        
						
			//RNN
						
			//x1 a x9
                        expr = new GRBLinExpr();
                        expr.addTerm(1.0, x1);
                        expr.addTerm(1.0, x2);
			expr.addTerm(1.0, x3);
                        expr.addTerm(0.0, x4);
                        expr.addTerm(1.0, x5);
                        expr.addTerm(1.0, x6);
                        expr.addTerm(1.0, x7);
                        expr.addTerm(1.0, x8);
                        expr.addTerm(1.0, x9);
                        model.addConstr(expr, GRB.GREATER_EQUAL, 0.0, "rnn1");
			
                        //Modelo
                        model.optimize();
                       
                        
                        //imprime valores na tela (nos jtexFields)
                        
                        jTextField1.setText(" " + x1.get(GRB.DoubleAttr.X));
                        
                        jTextField4.setText(" " + x2.get(GRB.DoubleAttr.X));
                        
                        jTextField13.setText(" " + x3.get(GRB.DoubleAttr.X));
                        
                        jTextField15.setText(" " + x4.get(GRB.DoubleAttr.X));
                        
                        jTextField16.setText(" " + x5.get(GRB.DoubleAttr.X));
                        
                        jTextField17.setText(" " + x6.get(GRB.DoubleAttr.X));
                        
                        jTextField18.setText(" " + x7.get(GRB.DoubleAttr.X));
                        
                        jTextField19.setText(" " + x8.get(GRB.DoubleAttr.X));
                        
                        jTextField20.setText(" " + x9.get(GRB.DoubleAttr.X));
                            
                    
      // Dispose of model and environment

      ambiente.dispose();
                       
		} catch (GRBException e) {
                    System.out.println("Error code: " + e.getErrorCode() + ". " +e.getMessage());
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Source Code Pro Medium", 0, 18)); // NOI18N
        jLabel1.setText("O Problema dos Estagiários");

        jLabel2.setFont(new java.awt.Font("Source Code Pro Medium", 0, 14)); // NOI18N
        jLabel2.setText("Entre com os valores desejados");

        jLabel3.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel3.setText("09:00 às 10:00");

        jLabel4.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel4.setText("10:00 às 11:00");

        jLabel5.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel5.setText("11:00 às 12:00");

        jLabel6.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel6.setText("12:00 às 13:00");

        jLabel7.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel7.setText("13:00 às 14:00");

        jLabel8.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel8.setText("14:00 às 15:00");

        jLabel9.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel9.setText("15:00 às 16:00");

        jLabel10.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel10.setText("16:00 às 17:00");

        jLabel11.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel11.setText("17:00 às 18:00");

        jTextField2.setText(" ");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jLabel13.setText("Horário de Trabalho");

        jLabel14.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel14.setText("Número de estagiários");

        jTextField3.setText(" ");

        jTextField5.setText(" ");

        jTextField6.setText(" ");

        jTextField7.setToolTipText("Nenhum estagiário começa a trabalhar neste horário");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.setText(" ");

        jTextField9.setText(" ");

        jTextField10.setText(" ");

        jTextField11.setText(" ");

        jButton1.setText("Otimizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpar Campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Source Code Pro Medium", 0, 14)); // NOI18N
        jLabel15.setText("Solução");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(" ");
        jTextField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel12.setText("Quantos estagiários devem começar a trabalhar às:");

        jLabel16.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("09:00");

        jLabel17.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("10:00");

        jLabel19.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("11:00");

        jLabel18.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("12:00");

        jLabel20.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("13:00");

        jLabel21.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("14:00");

        jLabel22.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("15:00");

        jLabel23.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("16:00");

        jLabel24.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("17:00");

        jLabel25.setFont(new java.awt.Font("Source Code Pro Medium", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("18:00");

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText(" ");
        jTextField4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField4.setEnabled(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField13.setEditable(false);
        jTextField13.setBackground(new java.awt.Color(255, 255, 255));
        jTextField13.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setText(" ");
        jTextField13.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField13.setEnabled(false);
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setEditable(false);
        jTextField14.setBackground(new java.awt.Color(255, 255, 255));
        jTextField14.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setText(" Nenhum estagiário");
        jTextField14.setToolTipText("Nenhum estagiário deve começar a trabalhar neste horário");
        jTextField14.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField14.setEnabled(false);

        jTextField15.setEditable(false);
        jTextField15.setBackground(new java.awt.Color(255, 255, 255));
        jTextField15.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setText(" ");
        jTextField15.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField15.setEnabled(false);

        jTextField16.setEditable(false);
        jTextField16.setBackground(new java.awt.Color(255, 255, 255));
        jTextField16.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField16.setText(" ");
        jTextField16.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField16.setEnabled(false);

        jTextField17.setEditable(false);
        jTextField17.setBackground(new java.awt.Color(255, 255, 255));
        jTextField17.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setText(" ");
        jTextField17.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField17.setEnabled(false);

        jTextField18.setEditable(false);
        jTextField18.setBackground(new java.awt.Color(255, 255, 255));
        jTextField18.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setText(" ");
        jTextField18.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField18.setEnabled(false);

        jTextField19.setEditable(false);
        jTextField19.setBackground(new java.awt.Color(255, 255, 255));
        jTextField19.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setText(" ");
        jTextField19.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField19.setEnabled(false);

        jTextField20.setEditable(false);
        jTextField20.setBackground(new java.awt.Color(255, 255, 255));
        jTextField20.setFont(new java.awt.Font("Source Code Pro Medium", 0, 11)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.setText(" ");
        jTextField20.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField20.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel14))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addGap(80, 80, 80)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel4)
                                .addComponent(jLabel11)
                                .addComponent(jLabel3)
                                .addComponent(jLabel8))
                            .addGap(129, 129, 129)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField13)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jTextField15)
                                    .addComponent(jTextField16)
                                    .addComponent(jTextField17)
                                    .addComponent(jTextField18)
                                    .addComponent(jTextField19)
                                    .addComponent(jTextField20)))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(261, 261, 261))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel19)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel22)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel23)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel11)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // Pega os valores do jtext, converte em int e passa para a função tppl
        //System.out.println(jTextField2.getText().trim());
        Integer rest1 = Integer.parseInt(jTextField2.getText().trim());
        Integer rest2 = Integer.parseInt(jTextField3.getText().trim());
        Integer rest3 = Integer.parseInt(jTextField5.getText().trim());
        Integer rest4 = Integer.parseInt(jTextField6.getText().trim());
        Integer rest5 = Integer.parseInt(jTextField8.getText().trim());
        Integer rest6 = Integer.parseInt(jTextField8.getText().trim());
        Integer rest7 = Integer.parseInt(jTextField9.getText().trim());
        Integer rest8 = Integer.parseInt(jTextField10.getText().trim());
        Integer rest9 = Integer.parseInt(jTextField11.getText().trim());
        
        TPPL(rest1, rest2, rest3, rest4, rest5, rest6, rest7, rest8, rest9);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Limpa os campos
        jTextField1.setText(" ");
        jTextField2.setText(" ");
        jTextField3.setText(" ");
        jTextField4.setText(" ");
        jTextField5.setText(" ");
        jTextField6.setText(" ");
        jTextField7.setText(" ");
        jTextField8.setText(" ");
        jTextField9.setText(" ");
        jTextField10.setText(" ");
        jTextField11.setText(" ");
        jTextField13.setText(" ");
        jTextField15.setText(" ");
        jTextField16.setText(" ");
        jTextField17.setText(" ");
        jTextField18.setText(" ");
        jTextField19.setText(" ");
        jTextField20.setText(" ");
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Mostra a interface*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
