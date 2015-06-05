package easymock.test;

import junit.framework.TestCase;

import org.easymock.classextension.EasyMock;
import org.easymock.classextension.IMocksControl;
import org.easymock.internal.LastControl;

import easymock.ClassUnderTest;
import easymock.Collaborator;

public class ExampleTest extends TestCase {

    private ClassUnderTest classUnderTest;
    private IMocksControl control;
    private Collaborator mock;

    protected void setUp() {
        classUnderTest = new ClassUnderTest();
        //control = EasyMock.createStrictControl();
        control = EasyMock.createNiceControl();
        
        mock = control.createMock(Collaborator.class);
        classUnderTest.addListener(mock);
    }

    public void testRemoveNonExistingDocument() {
    	control.replay();
        // This call should not lead to any notification
        // of the Mock Object: 
        classUnderTest.removeDocument("Does not exist");
    }
    
    public void testAddDocument() { 
    	mock.documentAdded("document");
    	control.replay();
        classUnderTest.addDocument("document", new byte[0]);
    }
    
    public void testDocumentChanged() { 
    	mock.documentAdded("document");
    	mock.documentChanged("document");
    	control.replay();
    	classUnderTest.addDocument("document", new byte[0]);
    	classUnderTest.addDocument("document", new byte[0]);
    	control.verify();
    }
    
    public void testDocumentChanged1() { 
    	mock.documentAdded("document");
    	mock.documentChanged("document");
    	//EasyMock.expectLastCall().times(3);
    	LastControl.lastControl().times(3);
    	control.replay();
    	classUnderTest.addDocument("document", new byte[0]);
    	classUnderTest.addDocument("document", new byte[0]);
    	classUnderTest.addDocument("document", new byte[0]);
    	classUnderTest.addDocument("document", new byte[0]);
    	control.verify();
    }
    
    public void testDocumentRemoved() { 
    	mock.documentAdded("document");
    	EasyMock.expect(mock.voteForRemoval("document")).andReturn((byte) 42);
    	mock.documentRemoved("document");
    	control.replay();
        classUnderTest.addDocument("document", new byte[0]);
        assertTrue(classUnderTest.removeDocument("document"));
    }
    
    public void testDocumentRemoveds() { 
    	mock.documentAdded("document1");
    	mock.documentAdded("document2");
    	
    	EasyMock.expect(mock.voteForRemovals("document1","document2")).andReturn((byte) 3);
    	mock.documentRemoved("document1");
    	mock.documentRemoved("document2");
    	control.replay();
        classUnderTest.addDocument("document1", new byte[0]);
        classUnderTest.addDocument("document2", new byte[0]);
        assertTrue(classUnderTest.removeDocuments("document1", "document2"));
    }
    
}