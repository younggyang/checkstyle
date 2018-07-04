////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2018 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package org.checkstyle.suppressionxpathfilter;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.checks.coding.NestedIfDepthCheck;

public class XpathRegressionNestedIfDepthTest extends AbstractXpathTestSupport {

    @Test
    public void testCorrect() throws Exception {
        final String checkName = NestedIfDepthCheck.class.getSimpleName();
        final File fileToProcess =
                new File(getPath(checkName,
                        "SuppressionXpathRegressionNestedIfDepth.java"));

        final DefaultConfiguration moduleConfig =
                createModuleConfig(NestedIfDepthCheck.class);

        final String[] expectedViolation = {
            "10:17: " + getCheckMessage(NestedIfDepthCheck.class,
                 NestedIfDepthCheck.MSG_KEY, 2, 1),
        };

        final List<String> expectedXpathQueries = Collections.singletonList(
            "/CLASS_DEF[@text='SuppressionXpathRegressionNestedIfDepth']/OBJBLOCK"
                + "/METHOD_DEF[@text='test']/SLIST/LITERAL_IF/SLIST/LITERAL_IF/SLIST/LITERAL_IF"
        );

        runVerifications(moduleConfig, fileToProcess, expectedViolation,
                expectedXpathQueries);
    }
}
