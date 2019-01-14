package tr.org.liderahenk.ntp.management.dialogs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.directory.studio.entryeditors.Messages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.liderconsole.core.current.UserSettings;
import tr.org.liderahenk.liderconsole.core.dialogs.DefaultTaskDialog;
import tr.org.liderahenk.liderconsole.core.exceptions.ValidationException;
import tr.org.liderahenk.liderconsole.core.ldap.listeners.LdapConnectionListener;
import tr.org.liderahenk.ntp.management.constants.NtpManagementConstants;

/**
 * Task execution dialog for ntp-management plugin.
 * 
 */
public class NtpManagementTaskDialog extends DefaultTaskDialog {
	
	private Label lblNTPServerIP;
	private Text textNTPServerIP;
	
	
	private static final Logger logger = LoggerFactory.getLogger(NtpManagementTaskDialog.class);
	
	// TODO do not forget to change this constructor if SingleSelectionHandler is used!
	public NtpManagementTaskDialog(Shell parentShell, Set<String> dnSet) {
		super(parentShell, dnSet);
	}

	@Override
	public String createTitle() {
		// TODO dialog title
		return Messages.getString("NTP Sunucu Ayarları");
	}

	@Override
	public Control createTaskDialogArea(Composite parent) {
		
		Composite composite = new Composite(parent, GridData.FILL);
		composite.setLayout(new GridLayout(2, false));

		GridData data= new GridData(SWT.FILL, SWT.FILL, true, true,1,1);
        data.widthHint=650;
        data.heightHint=500;
		
		//composite.setLayoutData(data);
		
			//add empty grid cell
		//new Label(composite, SWT.NONE);

        //NTP Server IP
		lblNTPServerIP = new Label(composite, SWT.NONE);
		lblNTPServerIP.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		lblNTPServerIP.setText("Sunucu Adresi"); //$NON-NLS-1$

		textNTPServerIP=new Text(composite, SWT.BORDER);
		textNTPServerIP.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,1,1));
		
		return composite;
	}

	@Override
	public void validateBeforeExecution() throws ValidationException {

		if(textNTPServerIP.getText().equals("")) {		
				throw new ValidationException(Messages.getString("Sunucu adresi boş olamaz"));
			}
			
	}
	
	@Override
	public Map<String, Object> getParameterMap() {
		Map<String, Object> params= new HashMap<>();
		params.put("server-address", textNTPServerIP.getText());
		return params;
	}

	@Override
	public String getCommandId() {
		// TODO command id which is used to match tasks with ICommand class in the corresponding Lider plugin
		return NtpManagementConstants.NTP_MANAGEMENT_COMMAND;
	}

	@Override
	public String getPluginName() {
		return NtpManagementConstants.PLUGIN_NAME;
	}

	@Override
	public String getPluginVersion() {
		return NtpManagementConstants.PLUGIN_VERSION;
	}
	
}
