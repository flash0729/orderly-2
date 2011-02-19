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

package com.gotometrics.hbase.format;

import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;

/** Decode and encode an integer into an immutable byte array in descending
 * order. The implementation reverses the sort order by encoding the logical
 * negation (1's complement) of the int value. It is easy to see why this is
 * the case. 
 *
 * Given x &gt; y, let b the most significant bit position in which x and y
 * differ. If we compare ~x with ~y, all positions in which x and y are equal
 * will remain equal. However, in the most significant bit position in which x
 * and y differ, x and y will have inverted their values, Thus ~x &lt; ~y as
 * y now has the greater value in position b.
 */
public class DescendingIntFormat extends IntFormat 
{
  /** This is a singleton class, instances may only be obtained by public static
   * get() method calls or static methods in com.gotometrics.hbase.format.FormatUtils
   */
  protected DescendingIntFormat() { }

  @Override
  public Order getOrder() { return Order.DESCENDING; }

  @Override
  public void encodeInt(final int i, final ImmutableBytesWritable ibw) {
    super.encodeInt(~i, ibw);
  }

  @Override
  public int decodeInt(final ImmutableBytesWritable bytes) {
    return ~super.decodeInt(bytes);
  }

  private static final DataFormat format = new DescendingIntFormat();
  public static DataFormat get() { return format; }
} 