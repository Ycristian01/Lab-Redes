/*
 * Laboratorio 1
 * REDES DE COMPUTACIÓN
 * Desarrollado por: 
 * - Sharon Figueroa, Roberto Acuña, Cristian Yepes, Kristell Urueta -
 * 201830
 */
package Acuna_figueroa_urueta_yepes;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class options extends javax.swing.JFrame {

    File archivo;
    int accepted;
    String Gx = "100110000010001110110110111";
    Boolean DataWordAccepted;
    
    int corregidos;
    /**
     * Creates new form options
     */
    public options() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    private String CodificarHAM(String s) {
        int b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
        String ham = s.substring(0,4) + " " + s.substring(4,7) + " " + s.substring(7,8)+ "  ";
        b3 = Integer.parseInt(ham.substring(9,10));
        b5 = Integer.parseInt(ham.substring(7,8));
        b6 = Integer.parseInt(ham.substring(6,7));
        b7 = Integer.parseInt(ham.substring(5,6));
        b9 = Integer.parseInt(ham.substring(3,4));
        b10 = Integer.parseInt(ham.substring(2,3));
        b11 = Integer.parseInt(ham.substring(1,2));
        b12 = Integer.parseInt(ham.substring(0,1));

        b1 = (b3+b5+b7+b9+b11) % 2;
        b2 = (b3+b6+b7+b10+b11) % 2;
        b4 = (b5+b6+b7+b12) % 2;
        b8 = (b9+b10+b11+b12) % 2;
        ham = s.substring(0,4) + b8 + s.substring(4,7) + b4 + s.substring(7,8)+ b2 + "" + b1;
        return ham;
    }
     private void CrearHAM(String FILE, String[] salida, int tam) {
        if(FILE.equals("")){FILE = "salida.txt";}
        String[] file = FILE.split(".txt");
        try{
            PrintWriter writer = new PrintWriter(file[0] + ".ham", "UTF-8");
            int i = 0;
            while(i < tam){
                String s = salida[i];                
                String CodeWord = CodificarHAM(s);
                writer.println(CodeWord);
                i++;
            }
            writer.close();
        } catch (IOException e) {
        }
    }
    private String LeerTXT(String FILE)
    {
        BufferedReader br = null;
        FileReader fr = null;
        String archivo = "";
        try {
            fr = new FileReader(FILE);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILE));

            while ((sCurrentLine = br.readLine()) != null) {
                archivo += sCurrentLine + "¶";
            }
            archivo = archivo.substring(0, archivo.length()-1);
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivo;
    }
    private void CrearTXT(String FILE, String[] salida, int tam) {
        if(FILE.equals("")){FILE = "salida.txt";}
        try{
            PrintWriter writer = new PrintWriter(FILE, "UTF-8");
            int i = 0;
            while(i < tam){
                String s = salida[i];
                writer.println(s);
                i++;
            }
            writer.close();
        } catch (IOException e) {
        }
    }
    
    private String DecodificarHAM(String s) {
        int b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
        b1 = Integer.parseInt(s.substring(11,12));
        b2 = Integer.parseInt(s.substring(10,11));
        b3 = Integer.parseInt(s.substring(9,10));
        b4 = Integer.parseInt(s.substring(8,9));
        b5 = Integer.parseInt(s.substring(7,8));
        b6 = Integer.parseInt(s.substring(6,7));
        b7 = Integer.parseInt(s.substring(5,6));
        b8 = Integer.parseInt(s.substring(4,5));
        b9 = Integer.parseInt(s.substring(3,4));
        b10 = Integer.parseInt(s.substring(2,3));
        b11 = Integer.parseInt(s.substring(1,2));
        b12 = Integer.parseInt(s.substring(0,1));
        int c1,c2,c4,c8;
        c1 = (b1+b3+b5+b7+b9+b11) % 2;
        c2 = (b2+b3+b6+b7+b10+b11) % 2;
        c4 = (b4+b5+b6+b7+b12) % 2;
        c8 = (b8+b9+b10+b11+b12) % 2;
        String c = "" + c8 + "" + c4 + "" + c2 + "" + c1;
        int PosError = Integer.parseInt(c, 2);
        String msgError = "Error en el bit " + PosError;
        switch(PosError)
        {
            case 0: msgError = "No hay error!"; break;// no hay error
            case 1: b1 = (b1 + 1) % 2;this.corregidos+=1;break;
            case 2: b2 = (b2 + 1) % 2;this.corregidos+=1;break;
            case 3: b3 = (b3 + 1) % 2;this.corregidos+=1;break;
            case 4: b4 = (b4 + 1) % 2;this.corregidos+=1;break;
            case 5: b5 = (b5 + 1) % 2;this.corregidos+=1;break;
            case 6: b6 = (b6 + 1) % 2;this.corregidos+=1;break;
            case 7: b7 = (b7 + 1) % 2;this.corregidos+=1;break;
            case 8: b8 = (b8 + 1) % 2;this.corregidos+=1;break;
            case 9: b9 = (b9 + 1) % 2;break;
            case 10: b10 = (b10 + 1) % 2;this.corregidos+=1;break;
            case 11: b11 = (b11 + 1) % 2;this.corregidos+=1;break;
            case 12: b12 = (b12 + 1) % 2;this.corregidos+=1;break;
        }
        String DataWord = "" + b12 + "" + b11 + "" + b10 + "" + b9 + "" + b7 + "" + b6 + "" + b5 + "" + b3;//s.substring(0,4) + s.substring(5,8) + s.substring(9,10);
        return DataWord;
    }
    
    private String LeerHAM(String FILE)
    {
        BufferedReader br = null;
        FileReader fr = null;
        String archivo = "";
        try {
            fr = new FileReader(FILE);
            br = new BufferedReader(fr);

            String sCurrentLine;
            this.corregidos=0;
            br = new BufferedReader(new FileReader(FILE));
            while ((sCurrentLine = br.readLine()) != null) {
                String DataWord = DecodificarHAM(sCurrentLine);
                archivo += DataWord;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivo;
    }
    
    private String[] BinToHex(String Bin, int tam, int k)
    {
        String[] hex = new String[tam+1];
        String[] b = Bin.split("");
        int j = 0;
        hex[j] = "";
        int bloque = 0;
        for (int i = 0; i < b.length; i=i+4) {
            String h = "";
            // h a caracter de 1 hex
            if(bloque % k == 0 && bloque != 0){j++; if(j<=tam){hex[j] = "";}}
            String binary = "" + b[i] + b[i+1] + b[i+2] + b[i+3];
            switch(binary)
            {
                case "0000": h = "0";break;
                case "0001": h = "1";break;
                case "0010": h = "2";break;
                case "0011": h = "3";break;
                case "0100": h = "4";break;
                case "0101": h = "5";break;
                case "0110": h = "6";break;
                case "0111": h = "7";break;
                case "1000": h = "8";break;
                case "1001": h = "9";break;
                case "1010": h = "a";break;
                case "1011": h = "b";break;
                case "1100": h = "c";break;
                case "1101": h = "d";break;
                case "1110": h = "e";break;
                case "1111": h = "f";break;
            }
            if(!b[i].equals("")){
                // cadena de 4 bits asignarselo a hex
                hex[j] += h;
                bloque += 4;
            }
            else{i-=3;}
        }
        return hex;
    }
    private static String hexToASCII(String hexValue)
    {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexValue.length()-1; i += 2)
        {
            String str = hexValue.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
    

    private String LeerCRC(String FILE)
    {
        BufferedReader br = null;
        FileReader fr = null;
        String archivo = "";
        try {
            fr = new FileReader(FILE);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILE));
            this.Gx = br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String DataWord = DecodeCRC(sCurrentLine, this.Gx);
                archivo += DataWord;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivo;
    }
    private String DecodeCRC(String DataWord, String Gx)
    {
        int TamWord = DataWord.length();
        int TamGx = Gx.length();
        String DataWordTemp = DataWord.substring(0,DataWord.length()-(TamGx-1));
        String Residuo = "";
        String Cociente = "";
        while(TamWord >= TamGx)
        {
            int j = 0;
            int r;
            int c;
            if(Integer.parseInt(DataWord.substring(0, 1)) == 0 || Integer.parseInt(Gx.substring(j, j+1)) == 0){Cociente += "0"; c=0;}
            else{Cociente +="1"; c=1;}
            r = (Integer.parseInt(DataWord.substring(0, 1)) + Integer.parseInt(Gx.substring(j, j+1))*c) % 2;
            Residuo = "" +r;
            j++;
            for (int k = 1; k < TamGx; k++) {
                r = (Integer.parseInt(DataWord.substring(k, k+1)) + Integer.parseInt(Gx.substring(j, j+1))*c) % 2;
                Residuo += r;
                j++;
            }
            DataWord = Residuo.substring(1) + DataWord.substring(TamGx);
            TamWord-=1;
        }
        String syndrome = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        syndrome = syndrome.substring(0, TamGx-1);
        if(Residuo.substring(1).equals(syndrome)){this.accepted++;}
        return DataWordTemp;
    }
    
private void CrearCRC(String FILE, String[] salida, int tam, String Gx) {
        if(FILE.equals("")){FILE = "salida.txt";}
        String[] file = FILE.split(".txt");
        try{
            PrintWriter writer = new PrintWriter(file[0] + ".txt", "UTF-8");
            PrintWriter writer2 = new PrintWriter(file[0] + ".crc", "UTF-8");
            int i = 0;
            writer.println(Gx);
            writer2.println(Gx);
            while(i < tam){
                String s = salida[i];
                String CodeWord = CodificadorCRC(s, Gx);
                writer.println(CodeWord);
                writer2.println(CodeWord);
                i++;
            }
            writer.close();
            writer2.close();
        } catch (IOException e) {
        }
    }
private String CodifCRC(String Dataword,String Gx){
  String Datawordtemp = Dataword;
        String Residuo = "";
        String Cociente = "";
        int tamword = Dataword.length();//k
        int tamgx = Gx.length();
       
            for (int j = 0; j < tamgx-1; j++) {//n-k
                Dataword += "0";
            }
        
            tamword = Dataword.length();//k
    int iter=tamword-tamgx+1;
    int f=1;
    String rs1=Dataword;
    String rs2="";
    while (f<=iter) {
        // j = 0;
        int c;
        int r;
        String rs="";
        String cs="";  
        rs2=""; 
        if(Integer.parseInt(rs1.substring(0,1))==1){
        cs="1"; 
        c=1;
        rs="1";
        } else {
        cs="0";
        c=0; 
        rs="0";
        }
        for (int i = 1; i < tamgx; i++) {     
        r=Integer.parseInt(Gx.substring(i, i+1))*c;
        rs+= Integer.toString(r);        
        }
        for (int i = 1; i < tamgx; i++) {
           int rtemp= Math.abs(Integer.parseInt(rs.substring(i, i+1))-Integer.parseInt(rs1.substring(i,i+1)));
            //System.out.println("A ver cuánto da esto: " + rtemp);
           rs2+=Integer.toString(rtemp);
        }
        rs1=Dataword.substring(tamgx+f,tamword+1);
        rs2+=Dataword.substring(0,1);
        f++;
        
    }
    return Datawordtemp+ rs2;
}
private String CodificadorCRC(String DataWord, String Gx)
    {
        String DataWordTemp = DataWord;
        String Residuo = "";
        String Cociente = "";
        int TamWord = DataWord.length();//k
        int TamGx = Gx.length();
            for (int j = 0; j < TamGx-1; j++) {//n-k
                DataWord += "0";
            }
        
        TamWord = DataWord.length();
        while(TamWord >= TamGx)
        {
            int j = 0;
            int r;
            int c;
            if(Integer.parseInt(DataWord.substring(0, 1)) == 0 || Integer.parseInt(Gx.substring(j, j+1)) == 0){
                Cociente += "0"; c=0;
            }
            else{
                Cociente +="1"; c=1;
            }
            r = (Integer.parseInt(DataWord.substring(0, 1)) + Integer.parseInt(Gx.substring(j, j+1))*c) % 2;
            Residuo = "" +r;
            j++;
           
            for (int k = 1; k < TamGx; k++) {
                //r = (Integer.parseInt(DataWord.substring(k, k+1)) + Integer.parseInt(Gx.substring(j, j+1))*c) % 2;
                r= Math.abs(Integer.parseInt(DataWord.substring(k, k+1))-Integer.parseInt(Gx.substring(j,j+1)));
                Residuo += r;
                j++;
            }
            DataWord = Residuo.substring(1) + DataWord.substring(TamGx);
            TamWord-=1;
        }
        return DataWordTemp + Residuo.substring(1);
    }
    void mostrarError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                titulo,
                JOptionPane.ERROR_MESSAGE);
    }
   
    
    private String[] HexToBin(String hexValue, int tam,int k)
    {
        String[] bin = new String[tam];
        String[] hex = hexValue.split("");//separa letra por letra
        int j = 0;
        bin[j]="";
        int bloque = 0;
        for (int i = 0; i < hex.length; i++) {
            String b = "";
                if(bloque % k == 0 && bloque != 0){
                    j++; 
                    if(j <= tam){
                     bin[j]="";}
                }   
            // b a cadena de 4 bits
            switch(hex[i].toLowerCase())//coge letra por letra
            {
                case "0": b = "0000";break;
                case "1": b = "0001";break;
                case "2": b = "0010";break;
                case "3": b = "0011";break;
                case "4": b = "0100";break;
                case "5": b = "0101";break;
                case "6": b = "0110";break;
                case "7": b = "0111";break;
                case "8": b = "1000";break;
                case "9": b = "1001";break;
                case "a": b = "1010";break;
                case "b": b = "1011";break;
                case "c": b = "1100";break;
                case "d": b = "1101";break;
                case "e": b = "1110";break;
                case "f": b = "1111";break;
            }
            // cadena de 4 bits asignarselo a bin
            if(!hex[i].equals("")){
                bin[j] += b;
                bloque += 4;         
            }
        }
        return bin;
    }
    
public LinkedList<String> toList() {
//Declaro el array
LinkedList<String> lines;
lines = new LinkedList<>();
String pathOfFile = archivo.toString();
int numberOfLines = getNumberOfLines(pathOfFile);
for (int i = 0; i < numberOfLines; i++) {
lines.add(getLineAtFile(pathOfFile, i));
}
return lines;
}

public int getNumberOfLines(String file) {
        String texto = "";
        int count = 0;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((texto = br.readLine()) != null) {
                count++;  //Mientras no sea el final de linea suma a un contador
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }


 public String getLineAtFile(String file, int index) {
        String texto = "";
        int count = 0;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str = br.readLine();

            while (str != null) {
                if (count == index) {
                    texto = str;
                    break;
                }
                str = br.readLine();
                count++;
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texto;
    }
private static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }
 
 
 
    void guardarArchivo() {
        // Crear un nuevo escritor
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo))) {
        } catch (IOException ex) {
            mostrarError("Error con archivo", "No se puede escribir en el archivo.");
        } catch (NullPointerException ex) {
            mostrarError("Datos faltantes", "Los datos en la tabla no están completos");
        } catch (NumberFormatException ex) {
            mostrarError("Formato equivocado", "Los números en la tabla no tienen el formato correcto");
        } catch (Exception ex) {
            mostrarError("Error", "Algo inesperado ocurrió");
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fc = new javax.swing.JFileChooser();
        decframe = new javax.swing.JFrame();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        gentxt = new javax.swing.JTextField();
        codif = new javax.swing.JButton();
        decod = new javax.swing.JButton();
        correctf = new javax.swing.JFrame();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btn_DecodificarHam = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtsalida = new javax.swing.JTextField();
        selectBuffer = new javax.swing.JButton();
        detectarbt = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtentrada = new javax.swing.JTextField();

        fc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcActionPerformed(evt);
            }
        });

        decframe.setTitle("Detección de errores");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/busqueda.png"))); // NOI18N
        jLabel2.setText("DETECCIÓN DE ERRORES");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Polinomio generador");

        gentxt.setText("110101");
        gentxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gentxtActionPerformed(evt);
            }
        });

        codif.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        codif.setText("Codificar CRC");
        codif.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        codif.setContentAreaFilled(false);
        codif.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        codif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codifActionPerformed(evt);
            }
        });

        decod.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        decod.setText("Decodificar CRC");
        decod.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        decod.setContentAreaFilled(false);
        decod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        decod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout decframeLayout = new javax.swing.GroupLayout(decframe.getContentPane());
        decframe.getContentPane().setLayout(decframeLayout);
        decframeLayout.setHorizontalGroup(
            decframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decframeLayout.createSequentialGroup()
                .addGroup(decframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(decframeLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gentxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(decframeLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, decframeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(decframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(decod, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codif, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );
        decframeLayout.setVerticalGroup(
            decframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decframeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(decframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gentxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(codif, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decod, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/busqueda.png"))); // NOI18N
        jLabel6.setText("CORRECCIÓN DE ERRORES");

        jLabel5.setText("Enviar datos y codificar");

        jButton1.setText("Codificar Hamming");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Decodificar y Recibir datos:");

        btn_DecodificarHam.setText("Decodificar Hamming");
        btn_DecodificarHam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DecodificarHamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout correctfLayout = new javax.swing.GroupLayout(correctf.getContentPane());
        correctf.getContentPane().setLayout(correctfLayout);
        correctfLayout.setHorizontalGroup(
            correctfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(correctfLayout.createSequentialGroup()
                .addGroup(correctfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(correctfLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(correctfLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(correctfLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_DecodificarHam)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        correctfLayout.setVerticalGroup(
            correctfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(correctfLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(correctfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(correctfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(btn_DecodificarHam))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Archivo salida:");

        txtsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsalidaActionPerformed(evt);
            }
        });

        selectBuffer.setBackground(java.awt.SystemColor.controlLtHighlight);
        selectBuffer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectBuffer.setText("Seleccionar");
        selectBuffer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectBuffer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBufferActionPerformed(evt);
            }
        });

        detectarbt.setBackground(java.awt.SystemColor.controlLtHighlight);
        detectarbt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detectarbt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/busqueda.png"))); // NOI18N
        detectarbt.setText("DETECTAR");
        detectarbt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        detectarbt.setContentAreaFilled(false);
        detectarbt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        detectarbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detectarbtActionPerformed(evt);
            }
        });

        jButton2.setBackground(java.awt.SystemColor.controlLtHighlight);
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prueba.png"))); // NOI18N
        jButton2.setText("CORREGIR");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 18)); // NOI18N
        jLabel4.setText("DETECCIÓN Y CORRECCIÓN DE ERRORES");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Archivo entrada:");

        txtentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtentradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(detectarbt, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtentrada)
                    .addComponent(txtsalida))
                .addGap(14, 14, 14)
                .addComponent(selectBuffer)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(selectBuffer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detectarbt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsalidaActionPerformed

    private void selectBufferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBufferActionPerformed
        // Agregar filtro a FileChooser
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("Archivos .txt", "txt", "texto");
        fc.setFileFilter(filter);

        // Mostrar el FileChooser
        int opcion = fc.showOpenDialog(this);

        // Si el usuario escogió abrir
        if (opcion == JFileChooser.APPROVE_OPTION) {
            // Asignar archivo y nombre.
            archivo = fc.getSelectedFile();
            txtentrada.setText(archivo.getAbsolutePath());
            
        }
        
        

    }//GEN-LAST:event_selectBufferActionPerformed

    private void codifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codifActionPerformed
        // TODO add your handling code here:
String texto = toList().toString();
texto=texto.replaceAll("[\\[\\]\"]", "");
System.out.println(texto);
String hex = asciiToHex(texto);
        // G(x) = x^7 + x^3 + 1 = 10001001
        String gx = gentxt.getText();// n-k+1 = 128 - PalDatos + 1
        this.accepted = 0;
        int k = 128;//155 - gx.length() + 1;
        double CantHex = hex.length();
        double limit = k/4;
        int tam = (int)Math.ceil(CantHex/limit);
        String[] bin = HexToBin(hex,tam,k);
        CrearCRC("salida",bin,tam,gx);
        JOptionPane.showMessageDialog(null, "Archivo CRC Codificado y Enviado!");
      
    }//GEN-LAST:event_codifActionPerformed

    private void detectarbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detectarbtActionPerformed
        decframe.pack();    
        decframe.setLocationRelativeTo(null);
        decframe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_detectarbtActionPerformed

    private void decodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodActionPerformed
         String salida = "salida.txt";
        if(!salida.equals("")){salida = txtsalida.getText();}
        String[] file = salida.split(".txt");
        String DataWord = LeerCRC(file[0]+".crc");
        int k = 128;//155-this.Gx.length() + 1;
        double CantHex = DataWord.length();
        double limit = k;
        int tam = (int)Math.ceil(CantHex/limit);
        String[] hex = BinToHex(DataWord, tam,k);
        String datos = "";
        for (int i = 0; i < tam; i++) {
            datos += hexToASCII(hex[i]);
        }
        String[] Lineas = datos.split("¶");
        CrearTXT(salida, Lineas, Lineas.length);
        if(this.accepted == tam){this.DataWordAccepted = true;}else{this.DataWordAccepted = false;}
        if(this.DataWordAccepted == true){
            JOptionPane.showMessageDialog(null, "Archivo CRC Decodificado y Recibido con exito!");
        }
        else{JOptionPane.showMessageDialog(null, "Archivo CRC Decodificado y Recibido Pero con Error(es)!","Warning",JOptionPane.WARNING_MESSAGE);}
        this.accepted=0;
    }//GEN-LAST:event_decodActionPerformed

    private void gentxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gentxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gentxtActionPerformed

    private void fcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fcActionPerformed

    private void btn_DecodificarHamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DecodificarHamActionPerformed
        // TODO add your handling code here:
        String salida = "salida.txt";
        if(!salida.equals("")){salida = txtsalida.getText();}
        String[] file = salida.split(".txt");
        String DataWord = LeerHAM(file[0]+".ham");
        int k = 8;
        double CantHex = DataWord.length();
        double limit = k;
        int tam = (int)Math.ceil(CantHex/limit);
        String[] hex = BinToHex(DataWord, tam,k);
        String datos = "";
        for (int i = 0; i < tam; i++) {
            datos += hexToASCII(hex[i]);
        }
        String[] Lineas = datos.split("¶");
        CrearTXT(salida, Lineas, Lineas.length);
        JOptionPane.showMessageDialog(null, "Archivo HAM Decodificado y Recibido con exito, se corrigieron " + this.corregidos + " error(es)!");
        this.corregidos=0;
    }//GEN-LAST:event_btn_DecodificarHamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String entrada = LeerTXT(txtentrada.getText());
        String hex = asciiToHex(entrada);
        int k = 8;
        double CantHex = hex.length();
        double limit = k/4;
        int tam = (int)Math.ceil(CantHex/limit);
        this.corregidos = 0;
        String[] bin = HexToBin(hex,tam,k);
        CrearHAM(txtsalida.getText(),bin,tam);
        JOptionPane.showMessageDialog(null, "Archivo HAM Codificado y Enviado!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtentradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtentradaActionPerformed

    //leer el codigo ascii que esta en el buffer .txt
    public static StringBuilder lector(String archivo) {
        StringBuilder sb = new StringBuilder();
        String linea;
        File f = new File(archivo);
        FileReader fr;
        BufferedReader br = null;

        try {
            try {
                fr = new FileReader(f);
                br = new BufferedReader(fr);

                while ((linea = br.readLine()) != null) {
                    sb.append(linea);
                    sb.append('\n');
                }
            } finally {
                if (br != null) {
                    br.close();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error entrada/salida");
        }

        return sb;
    }
    

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
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new options().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DecodificarHam;
    private javax.swing.JButton codif;
    private javax.swing.JFrame correctf;
    private javax.swing.JFrame decframe;
    private javax.swing.JButton decod;
    private javax.swing.JButton detectarbt;
    private javax.swing.JFileChooser fc;
    private javax.swing.JTextField gentxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton selectBuffer;
    private javax.swing.JTextField txtentrada;
    private javax.swing.JTextField txtsalida;
    // End of variables declaration//GEN-END:variables
}
