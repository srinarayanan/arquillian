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
package org.jboss.arquillian.impl.handler;

import junit.framework.Assert;

import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.impl.context.ClassContext;
import org.jboss.arquillian.impl.context.SuiteContext;
import org.jboss.arquillian.spi.ContainerMethodExecutor;
import org.jboss.arquillian.spi.ServiceLoader;
import org.jboss.arquillian.spi.event.suite.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Test case to 
 *
 * @author <a href="mailto:aknutsen@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivateRunModeTypeLocalTestCase
{
   @Mock
   private ServiceLoader serviceLoader;
   
   @Test
   public void shouldExportContainerMethodExecutorIfLocalMode() throws Exception 
   {
      ClassContext context = new ClassContext(new SuiteContext(serviceLoader));
      
      ActivateRunModeTypeClient handler = new ActivateRunModeTypeClient();
      handler.callback(context, new BeforeClass(TestWithRunModeLocal.class));
      
      Assert.assertNotNull(
            "Should have exported a " + ContainerMethodExecutor.class,
            context.get(ContainerMethodExecutor.class));
   }
   
   @Test
   public void shouldNotExportContainerMethodExecutorIfRemoteMode() throws Exception 
   {
      ClassContext context = new ClassContext(new SuiteContext(serviceLoader));
      
      ActivateRunModeTypeClient handler = new ActivateRunModeTypeClient();
      handler.callback(context, new BeforeClass(TestWithRunModeRemote.class));
      
      Assert.assertNull(
            "Should not have exported a " + ContainerMethodExecutor.class,
            context.get(ContainerMethodExecutor.class));
   }
   
   @Test
   public void shouldNotExportContainerMethodExecutorIfNoModeSpecified() throws Exception 
   {
      ClassContext context = new ClassContext(new SuiteContext(serviceLoader));
      
      ActivateRunModeTypeClient handler = new ActivateRunModeTypeClient();
      handler.callback(context, new BeforeClass(TestWithNoRunMode.class));
      
      Assert.assertNull(
            "Should not have exported a " + ContainerMethodExecutor.class,
            context.get(ContainerMethodExecutor.class));
   }

   @Run(RunModeType.AS_CLIENT)
   private static class TestWithRunModeLocal { }

   @Run(RunModeType.IN_CONTAINER)
   private static class TestWithRunModeRemote { }

   private static class TestWithNoRunMode { }
}
