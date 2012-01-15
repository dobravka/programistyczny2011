import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


/**
 * @author Ada
 */
public class Eksploatacja extends MIDlet implements CommandListener {
    
    private boolean midletPaused = false;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
private Form form;
private TextField Kilometres;
private TextField PricePerLiter;
private TextField Litres;
private TextField Login;
private Command okCommand5;
private Command okCommand6;
//</editor-fold>//GEN-END:|fields|0|

    public Eksploatacja() {
    }
    String path,name,dateInString,xml,login;
    DateField currentDate;
    private Date d;
    //private String photos = "fileconn.dir.memorycard";

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
/**
 * Initializes the application.
 * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
 */
private void initialize () {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
}//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Started point.
 */
public void startMIDlet () {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
switchDisplayable (null, getForm ());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
}//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
 */
public void resumeMIDlet () {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
}//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
/**
 * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
 * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
 * @param nextDisplayable the Displayable to be set
 */
public void switchDisplayable (Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
Display display = getDisplay ();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
if (alert == null) {
display.setCurrent (nextDisplayable);
} else {
display.setCurrent (alert, nextDisplayable);
}//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
}//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

  public void showInput(String path, String name){

  String l = Litres.getString();
  String p = PricePerLiter.getString();
  String k = Kilometres.getString();
  login = Login.getString();
  double l2 = Double.valueOf(l).doubleValue();
  double p2 = Double.valueOf(p).doubleValue();
  double k2 = Double.valueOf(k).doubleValue();
  double result = (l2*p2*100)/k2;
  String res = Double.toString(result);
  form = new Form("Input Value");
  form.append("Cost of 100km driving:");
  form.append(res);
  form.setCommandListener (this);
            
        StringBuffer sb = new StringBuffer();
        sb.append("<Fueling>\n");
        sb.append("   <field name=\"login\">"+login+"</field>\n"+"   <field name=\"litres\">"+l+"</field>\n"+"   <field name=\"priceperliter\">"+p+"</field>\n"+"   <field name=\"date\">"+dateInString+"</field>\n");
        sb.append("</Fueling>");

        xml = sb.toString();

  
  Display.getDisplay(this).setCurrent(form);
         try {
            String url = path + name;
            
            FileConnection fconn = (FileConnection)Connector.open(url, Connector.READ_WRITE);
            if (!fconn.exists()) {
                fconn.create();
            }
            OutputStream ops = fconn.openOutputStream(Long.MAX_VALUE);
            String nextData = xml;
            PrintStream printStream = new PrintStream(ops);
            printStream.println(nextData);        
            ops.close();
            fconn.close();
        }
        catch (IOException ioe) {
            System.out.println("IOException: "+ioe.getMessage());
        }
        catch (SecurityException se) {
            System.out.println("Security exception:" + se.getMessage());
        }      
          
   String d = "\nData has been saved!";
   form.append(d);
  }

  public void reset(String path, String name){
          try {
            String url = path + name;
            FileConnection fconn = (FileConnection)Connector.open(url, Connector.READ_WRITE);
            if (fconn.exists()) {
                fconn.delete();
            }
            fconn.close();
        }
        catch (IOException ioe) {
            System.out.println("IOException: "+ioe.getMessage());
        }
        catch (SecurityException se) {
            System.out.println("Security exception:" + se.getMessage());
        }     
          form = new Form("HistoryDeleted!");
          form.append("NoHistory");
          form.setCommandListener (this);
          Display.getDisplay(this).setCurrent(form);          
  }

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
 // write pre-action user code here
if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|36-preAction
if (command == okCommand5) {//GEN-END:|7-commandAction|1|36-preAction

    showInput(path, "fu.xml");
//GEN-LINE:|7-commandAction|2|36-postAction
} else if (command == okCommand6) {//GEN-LINE:|7-commandAction|3|42-preAction
 reset(path, "fu.xml");
//GEN-LINE:|7-commandAction|4|42-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|5|7-postCommandAction
}//GEN-END:|7-commandAction|5|7-postCommandAction
}//GEN-BEGIN:|7-commandAction|6|
//</editor-fold>//GEN-END:|7-commandAction|6|


//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|19-getter|0|19-preInit
/**
 * Returns an initiliazed instance of form component.
 * @return the initialized component instance
 */
public Form getForm () {
if (form == null) {//GEN-END:|19-getter|0|19-preInit
 // write pre-init user code here
form = new Form ("form", new Item[] { getLogin (), getLitres (), getPricePerLiter (), getKilometres () });//GEN-BEGIN:|19-getter|1|19-postInit
form.addCommand (getOkCommand5 ());
form.addCommand (getOkCommand6 ());
form.setCommandListener (this);//GEN-END:|19-getter|1|19-postInit
 // write post-init user code here
}//GEN-BEGIN:|19-getter|2|
return form;
}
//</editor-fold>//GEN-END:|19-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Litres ">//GEN-BEGIN:|20-getter|0|20-preInit
/**
 * Returns an initiliazed instance of Litres component.
 * @return the initialized component instance
 */
public TextField getLitres () {
if (Litres == null) {//GEN-END:|20-getter|0|20-preInit
 // write pre-init user code here
Litres = new TextField ("Litres", null, 32, TextField.ANY);//GEN-LINE:|20-getter|1|20-postInit

}//GEN-BEGIN:|20-getter|2|
return Litres;
}
//</editor-fold>//GEN-END:|20-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: PricePerLiter ">//GEN-BEGIN:|21-getter|0|21-preInit
/**
 * Returns an initiliazed instance of PricePerLiter component.
 * @return the initialized component instance
 */
public TextField getPricePerLiter () {
if (PricePerLiter == null) {//GEN-END:|21-getter|0|21-preInit
 // write pre-init user code here
PricePerLiter = new TextField ("Price per Liter", null, 32, TextField.ANY);//GEN-LINE:|21-getter|1|21-postInit

}//GEN-BEGIN:|21-getter|2|
return PricePerLiter;
}
//</editor-fold>//GEN-END:|21-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Kilometres ">//GEN-BEGIN:|32-getter|0|32-preInit
/**
 * Returns an initiliazed instance of Kilometres component.
 * @return the initialized component instance
 */
public TextField getKilometres () {
if (Kilometres == null) {//GEN-END:|32-getter|0|32-preInit
 // write pre-init user code here
Kilometres = new TextField ("Kilometres", null, 32, TextField.ANY);//GEN-LINE:|32-getter|1|32-postInit
 // write post-init user code here
}//GEN-BEGIN:|32-getter|2|
return Kilometres;
}
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand5 ">//GEN-BEGIN:|35-getter|0|35-preInit
/**
 * Returns an initiliazed instance of okCommand5 component.
 * @return the initialized component instance
 */
public Command getOkCommand5 () {
if (okCommand5 == null) {//GEN-END:|35-getter|0|35-preInit
 // write pre-init user code here
okCommand5 = new Command ("Save", Command.OK, 0);//GEN-LINE:|35-getter|1|35-postInit
 // write post-init user code here
}//GEN-BEGIN:|35-getter|2|
return okCommand5;
}
//</editor-fold>//GEN-END:|35-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand6 ">//GEN-BEGIN:|41-getter|0|41-preInit
/**
 * Returns an initiliazed instance of okCommand6 component.
 * @return the initialized component instance
 */
public Command getOkCommand6 () {
if (okCommand6 == null) {//GEN-END:|41-getter|0|41-preInit
 // write pre-init user code here
okCommand6 = new Command ("DeleteHistory", Command.OK, 1);//GEN-LINE:|41-getter|1|41-postInit
 // write post-init user code here
}//GEN-BEGIN:|41-getter|2|
return okCommand6;
}
//</editor-fold>//GEN-END:|41-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Login ">//GEN-BEGIN:|43-getter|0|43-preInit
/**
 * Returns an initiliazed instance of Login component.
 * @return the initialized component instance
 */
public TextField getLogin () {
if (Login == null) {//GEN-END:|43-getter|0|43-preInit
 // write pre-init user code here
Login = new TextField ("Login", null, 32, TextField.ANY);//GEN-LINE:|43-getter|1|43-postInit
 // write post-init user code here
}//GEN-BEGIN:|43-getter|2|
return Login;
}
//</editor-fold>//GEN-END:|43-getter|2|

    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    public void startApp() {
        path = "file:///root1/";
        d = new Date();
        currentDate = new DateField("", DateField.DATE_TIME);
        currentDate.setDate(d);
        dateInString = d.toString();
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    public void pauseApp() {
        midletPaused = true;
    }

    public void destroyApp(boolean unconditional) {
    }   

}


