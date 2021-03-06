/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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

/*
 *
 *
 *
 *
 *
 * ASM: a very small and fast Java bytecode manipulation framework
 * Copyright (c) 2000-2011 INRIA, France Telecom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package jdk.internal.org.objectweb.asm.tree;

import jdk.internal.org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents a required module with its name and access of a module descriptor.
 *
 * @author Remi Forax
 */
public class ModuleRequireNode {
    /**
     * The name of the required module.
     */
    public String module;

    /**
     * The access flags (see {@link jdk.internal.org.objectweb.asm.Opcodes}).
     * Valid values are <tt>ACC_TRANSITIVE</tt>, <tt>ACC_STATIC_PHASE</tt>,
     *        <tt>ACC_SYNTHETIC</tt> and <tt>ACC_MANDATED</tt>.
     */
    public int access;

    /**
     * Version at compile time of the required module or null.
     */
    public String version;

    /**
     * Constructs a new {@link ModuleRequireNode}.
     *
     * @param module
     *            the name of the required module.
     * @param access
     *            The access flags. Valid values are
     *            <tt>ACC_TRANSITIVE</tt>, <tt>ACC_STATIC_PHASE</tt>,
     *            <tt>ACC_SYNTHETIC</tt> and <tt>ACC_MANDATED</tt>
     *            (see {@link jdk.internal.org.objectweb.asm.Opcodes}).
     * @param version
     *            Version of the required module at compile time,
     *            null if not defined.
     */
    public ModuleRequireNode(final String module, final int access,
            final String version) {
        this.module = module;
        this.access = access;
        this.version = version;
    }

    /**
     * Makes the given module visitor visit this require directive.
     *
     * @param mv
     *            a module visitor.
     */
    public void accept(final ModuleVisitor mv) {
        mv.visitRequire(module, access, version);
    }
}
