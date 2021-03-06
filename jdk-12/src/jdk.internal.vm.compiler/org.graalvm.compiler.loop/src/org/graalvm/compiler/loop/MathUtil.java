/*
 * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.
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
 */


package org.graalvm.compiler.loop;

import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.graalvm.compiler.nodes.FixedNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.calc.BinaryArithmeticNode;
import org.graalvm.compiler.nodes.calc.FixedBinaryNode;
import org.graalvm.compiler.nodes.calc.SignedDivNode;
import org.graalvm.compiler.nodes.calc.UnsignedDivNode;
import org.graalvm.compiler.nodes.extended.GuardingNode;

import java.util.function.BiFunction;

/**
 * Utility methods to perform integer math with some obvious constant folding first.
 */
public class MathUtil {
    private static boolean isConstantOne(ValueNode v1) {
        return v1.isConstant() && v1.stamp(NodeView.DEFAULT) instanceof IntegerStamp && v1.asJavaConstant().asLong() == 1;
    }

    private static boolean isConstantZero(ValueNode v1) {
        return v1.isConstant() && v1.stamp(NodeView.DEFAULT) instanceof IntegerStamp && v1.asJavaConstant().asLong() == 0;
    }

    public static ValueNode add(StructuredGraph graph, ValueNode v1, ValueNode v2) {
        if (isConstantZero(v1)) {
            return v2;
        }
        if (isConstantZero(v2)) {
            return v1;
        }
        return BinaryArithmeticNode.add(graph, v1, v2, NodeView.DEFAULT);
    }

    public static ValueNode mul(StructuredGraph graph, ValueNode v1, ValueNode v2) {
        if (isConstantOne(v1)) {
            return v2;
        }
        if (isConstantOne(v2)) {
            return v1;
        }
        return BinaryArithmeticNode.mul(graph, v1, v2, NodeView.DEFAULT);
    }

    public static ValueNode sub(StructuredGraph graph, ValueNode v1, ValueNode v2) {
        if (isConstantZero(v2)) {
            return v1;
        }
        return BinaryArithmeticNode.sub(graph, v1, v2, NodeView.DEFAULT);
    }

    public static ValueNode divBefore(StructuredGraph graph, FixedNode before, ValueNode dividend, ValueNode divisor, GuardingNode zeroCheck) {
        return fixedDivBefore(graph, before, dividend, divisor, (dend, sor) -> SignedDivNode.create(dend, sor, zeroCheck, NodeView.DEFAULT));
    }

    public static ValueNode unsignedDivBefore(StructuredGraph graph, FixedNode before, ValueNode dividend, ValueNode divisor, GuardingNode zeroCheck) {
        return fixedDivBefore(graph, before, dividend, divisor, (dend, sor) -> UnsignedDivNode.create(dend, sor, zeroCheck, NodeView.DEFAULT));
    }

    private static ValueNode fixedDivBefore(StructuredGraph graph, FixedNode before, ValueNode dividend, ValueNode divisor, BiFunction<ValueNode, ValueNode, ValueNode> createDiv) {
        if (isConstantOne(divisor)) {
            return dividend;
        }
        ValueNode div = graph.addOrUniqueWithInputs(createDiv.apply(dividend, divisor));
        if (div instanceof FixedBinaryNode) {
            FixedBinaryNode fixedDiv = (FixedBinaryNode) div;
            if (before.predecessor() instanceof FixedBinaryNode) {
                FixedBinaryNode binaryPredecessor = (FixedBinaryNode) before.predecessor();
                if (fixedDiv.dataFlowEquals(binaryPredecessor)) {
                    fixedDiv.safeDelete();
                    return binaryPredecessor;
                }
            }
            graph.addBeforeFixed(before, fixedDiv);
        }
        return div;
    }
}
