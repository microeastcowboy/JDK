/*
 *  Copyright (c) 2003, 2018, Oracle and/or its affiliates. All rights reserved.
 *  ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/**
 * Provides five standard implementations of the standard JDBC <code>RowSet</code> implementation
 * interface definitions. These reference implementations are included with the J2SE version
 * 1.5 platform and represent the benchmark standard <code>RowSet</code> implementations as verified
 * by the Test Compatibility Kit (TCK) as mandated by the Java Community Process.
 * <br>
 *
 * <h3>1.0 Available JDBC RowSet Reference Implementations </h3>
 * The following implementations are provided:<br>
 *
 * <blockquote><code><b>JdbcRowSetImpl</b></code> - The <code>javax.sql.rowset.JdbcRowSet</code>
 * interface reference implementation. <br>
 * <br>
 * <code><b>CachedRowSetImpl</b></code> - The <code>javax.sql.rowset.CachedRowSet</code> interface
 * reference implementation.<br>
 * <br>
 * <code><b>WebRowSetImpl</b></code> - The <code>javax.sql.rowset.WebRowSet</code> interface
 * reference implementation.<br>
 * <br>
 * <code><b>FilteredRowSetImpl</b></code> - The <code>javax.sql.rowset.FilteredRowSet</code>
 * interface reference implementation.<br>
 * <br>
 * <code><b>JoinRowSetImpl</b></code> - The <code>javax.sql.rowset.JoinRowSet</code> interface
 * reference implementation.<br>
 * </blockquote>
 *
 * All details on their expected behavior, including their interactions with the <code>SyncProvider</code>
 * SPI and helper classes are provided in the interface definitions in the <code>javax.sql.rowset</code>
 * package specification.<br>
 *
 * <h3>2.0 Usage</h3>
 * The reference implementations represent robust implementations of the standard
 * <code>RowSet</code> interfaces defined in the <code>javax.sql.rowset</code> package.
 * All disconnected <code>RowSet</code> implementations, such as the <code>CachedRowSetImpl</code>
 * and <code>WebRowSetImpl</code>, are flexible enough to use the <code>SyncFactory</code> SPIs to
 * leverage non-reference implementation <code>SyncProvider</code> implementations to obtain
 * differing synchronization semantics. Furthermore, developers and vendors alike are free
 * to use these implementations and integrate them into their products just as they
 * can with to other components of the Java platform.<br>
 *
 * <h3>3.0 Extending the JDBC RowSet Implementations</h3>
 *
 * The JDBC <code>RowSet</code> reference implementations are provided as non-final
 * classes so that any developer can extend them to provide additional features
 * while maintaining the core required standard functionality and compatibility. It
 * is anticipated that many vendors and developers will extend the standard feature
 * set to their their particular needs. The website for JDBC Technology will
 * provider a portal where implementations can be listed, similar to the way it
 * provides a site for JDBC drivers.
 */
 package com.sun.rowset;
