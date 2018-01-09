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
                       
                        
                        //imprime valores na tela

                        
                        /*System.out.println(x1.get(GRB.StringAttr.VarName)
                         + " " + x1.get(GRB.DoubleAttr.X)); */
                        
                        jTextField12.setText("09:00 às 10:00: "+ " " + x1.get(GRB.DoubleAttr.X) +
                                "\n 10:00 às 11:00: " + " " + x2.get(GRB.DoubleAttr.X));
                        
                       /* jTextField12.setText("/n 10:00 às 11:00: "+ " " + x2.get(GRB.DoubleAttr.X));
                        
                        jTextField12.setText("11:00 às 12:00: "+ " " + x3.get(GRB.DoubleAttr.X));
                        
                        jTextField12.setText("12:00 às 13:00: "+ " " + x4.get(GRB.DoubleAttr.X));
                        
                        jTextField12.setText("13:00 às 14:00: "+ " " + x5.get(GRB.DoubleAttr.X));
                        
                        jTextField12.setText("14:00 às 15:00: "+ " " + x6.get(GRB.DoubleAttr.X));
                        
                        jTextField12.setText("15:00 às 16:00: "+ " " + x7.get(GRB.DoubleAttr.X));
                        
                        jTextField12.setText("16:00 às 17:00: "+ " " + x8.get(GRB.DoubleAttr.X));
                        
                        jTextField12.setText("17:00 às 18:00: "+ " " + x9.get(GRB.DoubleAttr.X));
                        */
                        
                       /*
                        System.out.println(x2.get(GRB.StringAttr.VarName)
                         + " " +x2.get(GRB.DoubleAttr.X));
                        
                        System.out.println(x3.get(GRB.StringAttr.VarName)
                         + " " +x3.get(GRB.DoubleAttr.X));
                        
                        System.out.println(x4.get(GRB.StringAttr.VarName)
                         + " " +x4.get(GRB.DoubleAttr.X));
                        
                        System.out.println(x5.get(GRB.StringAttr.VarName)
                         + " " +x5.get(GRB.DoubleAttr.X));
                        
                        System.out.println(x6.get(GRB.StringAttr.VarName)
                         + " " +x6.get(GRB.DoubleAttr.X));
                        
                        System.out.println(x7.get(GRB.StringAttr.VarName)
                         + " " +x7.get(GRB.DoubleAttr.X));
                        
                        System.out.println(x8.get(GRB.StringAttr.VarName)
                         + " " +x8.get(GRB.DoubleAttr.X));
                        
                        System.out.println(x9.get(GRB.StringAttr.VarName)
                         + " " +x9.get(GRB.DoubleAttr.X));
                        */
                        
                        System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));

                        int nSolutions = model.get(GRB.IntAttr.SolCount);
                        System.out.println("Número de Soluções encontradas: " + nSolutions);
      
                    
      // Dispose of model and environment

      ambiente.dispose();
                       
		} catch (GRBException e) {
                    System.out.println("Error code: " + e.getErrorCode() + ". " +e.getMessage());
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jTextField12 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Source Code Pro Medium", 0, 18)); // NOI18N
        jLabel1.setText("O Problema dos Estagiários");

        jLabel2.setFont(new java.awt.Font("Source Code Pro Medium", 0, 14)); // NOI18N
        jLabel2.setText("Entre com os valores desejados");

        jLabel3.setText("09:00 às 10:00");

        jLabel4.setText("10:00 às 11:00");

        jLabel5.setText("11:00 às 12:00");

        jLabel6.setText("12:00 às 13:00");

        jLabel7.setText("13:00 às 14:00");

        jLabel8.setText("14:00 às 15:00");

        jLabel9.setText("15:00 às 16:00");

        jLabel10.setText("16:00 às 17:00");

        jLabel11.setText("17:00 às 18:00");

        jTextField2.setText(" ");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Horário de Trabalho");

        jLabel14.setText("Número de estagiários");

        jTextField3.setText(" ");

        jTextField5.setText(" ");

        jTextField6.setText(" ");

        jTextField7.setToolTipText("Nenhum estagiário começa a trabalhar neste horário");
        jTextField7.setEnabled(false);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(111, 111, 111)
                                .addComponent(jLabel14))
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
                                .addGap(175, 175, 175)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButton2)
                        .addGap(80, 80, 80)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField12)
                        .addGap(2, 2, 2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
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
        jTextField2.setText(" ");
        jTextField3.setText(" ");
        jTextField5.setText(" ");
        jTextField6.setText(" ");
        jTextField7.setText(" ");
        jTextField8.setText(" ");
        jTextField9.setText(" ");
        jTextField10.setText(" ");
        jTextField11.setText(" ");
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed


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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    public javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
