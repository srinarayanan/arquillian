/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.impl.context;

import org.jboss.arquillian.impl.handler.ContainerTestExecuter;
import org.jboss.arquillian.impl.handler.TestCaseEnricher;
import org.jboss.arquillian.spi.event.suite.Before;
import org.jboss.arquillian.spi.event.suite.Test;

/**
 * StandaloneProfileBuilder
 *
 * @author <a href="mailto:aknutsen@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class StandaloneProfileBuilder extends ClientProfileBuilder
{
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.context.ProfileBuilder#buildTestContext(org.jboss.arquillian.impl.context.TestContext)
    */
   @Override
   public void buildTestContext(TestContext context, Object testInstance)
   {
      context.register(Before.class, new TestCaseEnricher());
      context.register(Test.class, new ContainerTestExecuter());
   }
}
