package FrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import mining.ExtractTemporalFact;

public class Interface extends javax.swing.JFrame {

	  /**
     * Creates new form MailJframe
     */
    public Interface() {
        initComponents();
        
    }

  
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTextArearObject = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextMailAddressSender = new javax.swing.JTextField();
        jTextMailAddressReceiver = new javax.swing.JTextField();
        jPassworSender = new javax.swing.JPasswordField();
        jTextmailsubject = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonSendMail = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabelpieceJointe = new javax.swing.JLabel();
       
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); 
        jLabel1.setText("Temporal Annotation");

        jTextArea1.setColumns(25);
        jTextArea1.setRows(9);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Expediteur");

        jLabel3.setText("Mot de passe");

        jLabel4.setText("Destinataire");

        jLabel5.setText("Subjet");

        jLabel6.setText("Message");

        jButtonSendMail.setText("Envoyer");
        jButtonSendMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendMailActionPerformed(evt);
            }
        });

        setLocationRelativeTo(null);

      

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("dbpedia.png"))); 

        javax.swing.GroupLayout jTextArearObjectLayout = new javax.swing.GroupLayout(jTextArearObject);
        jTextArearObject.setLayout(jTextArearObjectLayout);
        jTextArearObjectLayout.setHorizontalGroup(
            jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTextArearObjectLayout.createSequentialGroup()
                .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jTextArearObjectLayout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSendMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jTextArearObjectLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jTextArearObjectLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(jLabelpieceJointe, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jTextArearObjectLayout.createSequentialGroup()
                                .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextMailAddressSender)
                                    .addComponent(jTextMailAddressReceiver)
                                    .addComponent(jPassworSender)
                                    .addComponent(jTextmailsubject)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addContainerGap())
        );
        jTextArearObjectLayout.setVerticalGroup(
            jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTextArearObjectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTextArearObjectLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSendMail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTextArearObjectLayout.createSequentialGroup()
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextMailAddressSender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jPassworSender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextMailAddressReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextmailsubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jTextArearObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabelpieceJointe, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        ))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextArearObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextArearObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonSendMailActionPerformed(java.awt.event.ActionEvent evt) {
    	ExtractTemporalFact ex = new ExtractTemporalFact();
    	 
    	String exp=jTextMailAddressSender.getText();
    	String pwd=jPassworSender.getText();
    	String dest=jTextMailAddressReceiver.getText();
    	
    	String[] lines = jTextArea1.getText().split("\\n");
        String msg="";
        for(String s:lines){
            msg = msg+s;
        }
        try {
	//		mail.sendMail(jTextmailsubject.getText(), msg);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

        
    }

 
    public static void main(String args[]) {
           try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
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
              java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
                
            }
        });
    }
    private javax.swing.JButton jButtonSendMail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelpieceJointe;
    private javax.swing.JPasswordField jPassworSender;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jTextArearObject;
    private javax.swing.JTextField jTextMailAddressReceiver;
    private javax.swing.JTextField jTextMailAddressSender;
    private javax.swing.JTextField jTextmailsubject;
    
}
