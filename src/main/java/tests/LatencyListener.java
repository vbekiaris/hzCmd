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
import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class LatencyListener implements EntryListener<Integer, Data>, DataSerializable {

    Histogram h = new ConcurrentHistogram(TimeUnit.SECONDS.toNanos(30), 2);

    //AtomicInteger i = new AtomicInteger();
    //final int sampleSize=100000;
    //long latencys[] = new long[sampleSize];

    public LatencyListener() { }

    public void entryAdded(EntryEvent<Integer, Data> e) {

        Data then = e.getValue();
        long now = System.currentTimeMillis();
        long latency = now - then.now;

        if(latency >= 0 ) {
            h.recordValue(latency);
        }

        //latencys[i.getAndIncrement()]=latency;
        //i.set( i.get() % sampleSize );
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
