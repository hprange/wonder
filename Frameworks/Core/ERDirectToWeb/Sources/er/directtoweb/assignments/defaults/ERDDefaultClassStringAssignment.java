package er.directtoweb.assignments.defaults;

import org.apache.log4j.Logger;

import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eocontrol.EOKeyValueUnarchiver;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.directtoweb.assignments.ERDAssignment;
import er.directtoweb.assignments.ERDComputingAssignmentInterface;
import er.extensions.foundation.ERXDictionaryUtilities;

/**
 * An assignment to auto-compute a unique(ish), human-readable DOM class strings from the d2wContext for Selenium, CSS, Ajax, Javascript, etc.
 *
 * This assignment provides defaults for the following keys:
 * <ul>
 * <li><code>classForTask</code></li>
 * </ul>
 * 
 * To use: Bind D2W component class binding to d2wContext.class (or d2wContext.classForTask)
 * 
 * You may also override these auto computed id by setting rules for the above keys, if necessary.
 * 
 * @author mendis
 *
 */
public class ERDDefaultClassStringAssignment extends ERDAssignment {
	/** logging support */
    public final static Logger log = Logger.getLogger(ERDDefaultClassStringAssignment.class);
	
    /** holds the array of keys this assignment depends upon */
    protected static final NSDictionary keys = ERXDictionaryUtilities.dictionaryWithObjectsAndKeys( new Object [] {
        new NSArray(new Object[] {"task", "subTask"}), "classForTask",
    });

    /**
     * Implementation of the {@link ERDComputingAssignmentInterface}. This array
     * of keys is used when constructing the
     * significant keys for the passed in keyPath.
     * @param keyPath to compute significant keys for.
     * @return array of context keys this assignment depends upon.
     */
    public NSArray dependentKeys(String keyPath) {
        return (NSArray)keys.valueForKey(keyPath);
    }
    
    /**
     * Static constructor required by the EOKeyValueUnarchiver
     * interface. If this isn't implemented then the default
     * behavior is to construct the first super class that does
     * implement this method. Very lame.
     * @param eokeyvalueunarchiver to be unarchived
     * @return decoded assignment of this class
     */
     // ENHANCEME: Only ever need one of these assignments.
    public static Object decodeWithKeyValueUnarchiver(EOKeyValueUnarchiver eokeyvalueunarchiver)  {
        return new ERDDefaultClassStringAssignment(eokeyvalueunarchiver);
    }
    
    /** 
     * Public constructor
     * @param u key-value unarchiver used when unarchiving
     *		from rule files. 
     */
    public ERDDefaultClassStringAssignment (EOKeyValueUnarchiver u) { super(u); }
    
    /** 
     * Public constructor
     * @param key context key
     * @param value of the assignment
     */
    public ERDDefaultClassStringAssignment (String key, Object value) { super(key,value); }
    
    /**
     * A DOM class based on the task and subTask
     * 
     * @param c d2w context
     * @return an id representing the section in a tab page
     */
    public Object classForTask(D2WContext c) {
    	String classForTask = "task";
    	classForTask += (c.task() != null) ? " " + c.task() : "";
    	classForTask += (c.valueForKey("subTask") != null) ? " " + c.valueForKey("subTask") : "";
    	return classForTask;
    }
}