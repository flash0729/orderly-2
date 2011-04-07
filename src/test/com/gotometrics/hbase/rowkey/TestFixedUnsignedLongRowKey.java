/* Copyright 2011 GOTO Metrics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.gotometrics.hbase.rowkey;

import org.apache.hadoop.io.LongWritable;

public class TestFixedUnsignedLongRowKey 
  extends TestFixedUnsignedLongWritableRowKey
{
  @Override
  public RowKey createRowKey() {
    return new FixedUnsignedLongRowKey().setOrder(r.nextBoolean() ? 
        Order.ASCENDING : Order.DESCENDING);
  }

  @Override
  public Object createObject() {
    return Long.valueOf(((LongWritable)super.createObject()).get());
  }

  @Override
  public int compareTo(Object o1, Object o2) {
    long x = (Long) o1 ^ Long.MIN_VALUE,
         y = (Long) o2 ^ Long.MIN_VALUE;

    return ((x > y) ? 1 : 0) - ((y > x) ? 1 : 0);
  }
}
