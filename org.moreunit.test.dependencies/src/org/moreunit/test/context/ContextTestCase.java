package org.moreunit.test.context;

import org.junit.Rule;

public abstract class ContextTestCase
{
    @Rule
    public final TestContextRule context = new TestContextRule();
}
