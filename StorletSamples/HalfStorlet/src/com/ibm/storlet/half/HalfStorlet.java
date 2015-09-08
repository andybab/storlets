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

/*============================================================================
 07-Jul-2015    cdoron     Initial implementation.
 ===========================================================================*/

package com.ibm.storlet.half;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.storlet.common.IStorlet;
import com.ibm.storlet.common.StorletException;
import com.ibm.storlet.common.StorletInputStream;
import com.ibm.storlet.common.StorletLogger;
import com.ibm.storlet.common.StorletObjectOutputStream;
import com.ibm.storlet.common.StorletOutputStream;

public class HalfStorlet implements IStorlet {
	@Override
	public void invoke(ArrayList<StorletInputStream> inputStreams,
			ArrayList<StorletOutputStream> outputStreams,
			Map<String, String> parameters, StorletLogger log)
			throws StorletException {
		log.emitLog("HalfStorlet Invoked");

		StorletInputStream sis = inputStreams.get(0);

		StorletObjectOutputStream storletObjectOutputStream;
		storletObjectOutputStream = (StorletObjectOutputStream) outputStreams
				.get(0);
		storletObjectOutputStream.setMetadata(sis.getMetadata());

		/*
		 * Copy every other byte from input stream to output stream
		 */
		log.emitLog("Copying every other byte");
		StorletInputStream psis = (StorletInputStream) inputStreams.get(0);
		InputStream is;
		is = psis.getStream();

		OutputStream os = storletObjectOutputStream.getStream();
		try {
			log.emitLog(new Date().toString() + "About to read from input");
			int a;
			boolean bool = true;
			while ((a = is.read()) != -1) {
				if (bool)
					os.write(a);
				bool = !bool;
			}
		} catch (Exception e) {
			log.emitLog("Copying every other byte from input stream to output stream failed: "
					+ e.getMessage());
			throw new StorletException(
					"Copying every other byte from input stream to output stream failed: "
							+ e.getMessage());
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
			}
		}
		log.emitLog("HalfStorlet Invocation done");
	}
}
