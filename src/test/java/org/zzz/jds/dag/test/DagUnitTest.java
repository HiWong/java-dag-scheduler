/**
 * 
 */
package org.zzz.jds.dag.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.zzz.jds.dag.Dag;
import org.zzz.jds.dag.DagCycleException;
import org.zzz.jds.dag.DuplicatedEdgeException;
import org.zzz.jds.dag.Vertex;

/**
 * @author ming luo
 *
 */
public class DagUnitTest {

    private Dag fiveVerticeDag = new Dag();
    Vertex<Integer> N1 = new Vertex<>();
    Vertex<Integer> N2 = new Vertex<>();
    Vertex<Integer> N3 = new Vertex<>();
    Vertex<Integer> N4 = new Vertex<>();
    Vertex<Integer> N5 = new Vertex<>();
    /**
     * @throws java.lang.Exception
     * 
     */
    @Before
    public void setUp() throws Exception {
        fiveVerticeDag.putEdge(N1, N2);
        fiveVerticeDag.putEdge(N2, N3);
        fiveVerticeDag.putEdge(N1, N4);
        fiveVerticeDag.putEdge(N4, N5);
        fiveVerticeDag.putEdge(N5, N3);
    }

    @Test (expected=DagCycleException.class)
    public void testFailSelfLoopVertex() throws DagCycleException, DuplicatedEdgeException{
        Vertex<Integer> n1 = new Vertex<>();
        Vertex<Integer> n2 = new Vertex<>();
        Dag dag = new Dag();
        dag.putEdge(n1, n2);
        dag.putEdge(n2, n1);
        
        assertFalse(dag.isDag());
    }

    @Test (expected=DagCycleException.class)
    public void testOneSelfLoopVertex() throws DagCycleException, DuplicatedEdgeException{
    	Vertex<Integer> n1 = new Vertex<>();
        Dag dag = new Dag();
        dag.putEdge(n1, n1);

        assertFalse(dag.isDag());
    }

    @Test
    public void test5VertexDag() {
        assertTrue(fiveVerticeDag.isDag());
    }

    @Test
    public void testAddVertex5VertexDag() throws DagCycleException, DuplicatedEdgeException {
        Vertex<Integer> n6 = new Vertex<>();
        fiveVerticeDag.putEdge(N5, n6);
        fiveVerticeDag.putEdge(N3, n6);
        assertTrue(fiveVerticeDag.isDag());
    }

    @Test (expected=DagCycleException.class)
    public void testAddLoopin5VertexDag() throws DagCycleException, DuplicatedEdgeException {
        fiveVerticeDag.putEdge(N5, N1);
        assertFalse(fiveVerticeDag.isDag());
    }

    @Test (expected=DuplicatedEdgeException.class)
    public void testAddDuplicateEdgein5VertexDag() throws DagCycleException, DuplicatedEdgeException {
        fiveVerticeDag.putEdge(N2, N3);
        assertTrue(fiveVerticeDag.isDag());
    }
}
