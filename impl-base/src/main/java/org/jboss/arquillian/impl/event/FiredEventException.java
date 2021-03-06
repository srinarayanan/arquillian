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
package org.jboss.arquillian.impl.event;

import org.jboss.arquillian.spi.Context;
import org.jboss.arquillian.spi.event.Event;

/**
 * FiredEventException
 *
 * @author <a href="mailto:aknutsen@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class FiredEventException extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   private transient Event source;
   private transient Context context;
   
   public FiredEventException(Context context, Event source, Throwable cause)
   {
      super(cause);
      this.source = source;
      this.context = context;
   }
   
   public Event getSource()
   {
      return source;
   }
   
   public Context getContext()
   {
      return context;
   }
}
