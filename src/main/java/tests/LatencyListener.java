/*
 * Copyright (c) 2008-2015, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import java.io.IOException;


public class LatencyListener implements EntryListener<Integer, Long>, DataSerializable {

    int i=0;
    final int sampleSize=100000;
    long latencys[] = new long[sampleSize];

    public LatencyListener() { }

    public void entryAdded(EntryEvent<Integer, Long> e) {

        long then = e.getValue();
        long now = System.currentTimeMillis();
        long latency = now - then;

        latencys[i++]=latency;
        i = i % sampleSize;
    }

    public void entryRemoved(EntryEvent e) {
    }

    public void entryUpdated(EntryEvent e) {
    }

    public void entryEvicted(EntryEvent e) {
    }

    public void mapEvicted(MapEvent mapEvent) {
    }

    public void mapCleared(MapEvent mapEvent) {
    }

    public void writeData(ObjectDataOutput objectDataOutput) throws IOException {

    }

    public void readData(ObjectDataInput objectDataInput) throws IOException {

    }
}
