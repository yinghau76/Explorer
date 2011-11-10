package com.mediatek.android.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Explorer extends ListActivity {
	
	private List<String> items = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
    	super.onCreate(icicle);
        setContentView(R.layout.directory_list);
        fillWithRoot();
    }
    
    private void fill(File[] files) 
    {
    	items = new ArrayList<String>();
    	items.add(getString(R.string.on_top));
        for( File file : files )
        	items.add(file.getPath());
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, R.layout.file_row, items);
        setListAdapter(fileList);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) 
    {
    	int selectionRowID = (int)getSelectionRowID();
    	if (selectionRowID==0){
    		fillWithRoot();
    	} else {
    		File file = new File(items.get(selectionRowID));
    		if (file.isDirectory())
    			fill(file.listFiles());
    		else
    			AlertDialog.show(this, "Not a Directory", "That's a file, not a directory", "Cancel", false);
    	}
    }
    
    private void fillWithRoot() {
        fill(new File("/").listFiles());
    }
}