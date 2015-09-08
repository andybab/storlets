/*----------------------------------------------------------------------------
 * Copyright IBM Corp. 2015, 2015 All Rights Reserved
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
 * Limitations under the License.
 * ---------------------------------------------------------------------------
 */

/*
 * Author: eranr
 */
package com.ibm.storlet.common;

public class ObjectRequestEntry {
	private StorletObjectOutputStream objectStream = null;

	public synchronized StorletObjectOutputStream get()
			throws InterruptedException {
		if (objectStream == null)
			wait();

		return objectStream;
	}

	public synchronized void put(StorletObjectOutputStream objectStream)
			throws InterruptedException {
		this.objectStream = objectStream;
		notify();
	}
}