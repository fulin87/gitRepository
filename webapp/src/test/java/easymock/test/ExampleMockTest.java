package easymock.test;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.easymock.internal.LastControl;

import easymock.ClassUnderTest;
import easymock.Collaborator;

public class ExampleMockTest extends TestCase {

    private ClassUnderTest classUnderTest;
    private Collaborator mock;
    private Collaborator mock1;
    
    protected void setUp() {
        mock = EasyMock.createMock(Collaborator.class); // 1
        mock1 = EasyMock.createMock(Collaborator.class); // 1
        classUnderTest = new ClassUnderTest();
        classUnderTest.addListener(mock);
        //classUnderTest.addListener(mock1);
    }

    public void testRemoveNonExistingDocument() {
        // 2 (we do not expect anything)
    	EasyMock.replay(mock); // 3
        classUnderTest.removeDocument("Does not exist");
    }
    
    public void testAddDocument() {
        mock.documentAdded("New Document"); // 2
        EasyMock.replay(mock); // 3
        classUnderTest.addDocument("New Document", new byte[0]); 
        //verify(mock);
    }
    
    public void testAddAndChangeDocument() {
        mock.documentAdded("Document");
        mock.documentChanged("Document");
        mock.documentChanged("Document");
        mock.documentChanged("Document");
        EasyMock.replay(mock);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        EasyMock.verify(mock);
    }
    
    public void testAddAndChangeDocument1() {
        mock.documentAdded("Document");
        mock.documentChanged("Document");
        EasyMock.expectLastCall().times(3);
        //
        EasyMock.replay(mock);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        EasyMock.verify(mock);
    }
    
    public void testVoteForRemoval() {
        mock.documentAdded("Document");   // expect document addition
        // expect to be asked to vote for document removal, and vote for it
        EasyMock.expect(mock.voteForRemoval("Document")).andReturn((byte) 42);
        mock.documentRemoved("Document"); // expect document removal
        EasyMock.replay(mock);
        classUnderTest.addDocument("Document", new byte[0]);
        assertTrue(classUnderTest.removeDocument("Document"));
        EasyMock.verify(mock);
    }

    public void testVoteAgainstRemoval() {
        mock.documentAdded("Document");   // expect document addition
        // expect to be asked to vote for document removal, and vote against it
        EasyMock.expect(mock.voteForRemoval("Document")).andReturn((byte) -42);
        EasyMock.replay(mock);
        classUnderTest.addDocument("Document", new byte[0]);
        assertFalse(classUnderTest.removeDocument("Document"));
        EasyMock.verify(mock);
    }
}
