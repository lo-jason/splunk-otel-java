/*
 * Copyright Splunk Inc.
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

package com.splunk.opentelemetry.profiler.snapshot;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class SpanTrackerProviderTest {
  private final SpanTrackerProvider provider = SpanTrackerProvider.INSTANCE;

  @AfterEach
  void teardown() {
    SpanTrackerProvider.INSTANCE.configure(SpanTracker.NOOP);
  }

  @Test
  void provideNoopSpanTrackerWhenNotConfigured() {
    assertSame(SpanTracker.NOOP, provider.get());
  }

  @Test
  void providedConfiguredSpanTracker() {
    var tracker = new InMemorySpanTracker();
    provider.configure(tracker);
    assertSame(tracker, provider.get());
  }

  @Test
  void doNotAllowNullSpanTrackers() {
    assertThrows(Exception.class, () -> provider.configure(null));
  }
}
