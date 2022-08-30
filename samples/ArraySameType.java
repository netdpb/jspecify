/*
 * Copyright 2022 The JSpecify Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jspecify.nullness.NullMarked;
import org.jspecify.nullness.Nullable;
import org.jspecify.nullness.NullnessUnspecified;

@NullMarked
abstract class ArraySameType {
  interface Lib<T> {}

  abstract void useArray(Lib<Object[]> l);

  abstract void useArrayOfUnspec(Lib<@NullnessUnspecified Object[]> l);

  abstract void useArrayOfUnionNull(Lib<@Nullable Object[]> l);

  void client(Lib<Object[]> l) {
    useArray(l);
    // jspecify_nullness_not_enough_information jspecify_but_expect_nothing
    useArrayOfUnspec(l);

    // test:parameter-type:useArrayOfUnionNulll#l=Lib!<Object?[]!>
    // test:argument-type:useArrayOfUnionNull#l=Lib!<Object![]!>
    // test:incompatible-argument:useArrayOfUnionNull#l
    useArrayOfUnionNull(l);
  }

  void clientUnspec(Lib<@NullnessUnspecified Object[]> l) {
    // jspecify_nullness_not_enough_information jspecify_but_expect_nothing
    useArray(l);
    // jspecify_nullness_not_enough_information jspecify_but_expect_nothing
    useArrayOfUnspec(l);
    // jspecify_nullness_not_enough_information jspecify_but_expect_nothing
    useArrayOfUnionNull(l);
  }

  void clientUnionNull(Lib<@Nullable Object[]> l) {
    // test:parameter-type:useArray#l=Lib!<Object![]!>
    // test:argument-type:useArray#l=Lib!<Object?[]!>
    // test:incompatible-argument:useArray#l
    useArray(l);
    // jspecify_nullness_not_enough_information jspecify_but_expect_nothing
    useArrayOfUnspec(l);
    useArrayOfUnionNull(l);
  }
}
