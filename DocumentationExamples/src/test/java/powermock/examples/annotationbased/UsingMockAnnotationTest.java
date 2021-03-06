/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package powermock.examples.annotationbased;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.examples.annotationbased.dao.SomeDao;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertSame;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

/**
 * Test of the {@link SomeService} when using the {@link Mock} annotation to
 * create and inject mocks.
 */
@RunWith(PowerMockRunner.class)
public class UsingMockAnnotationTest {

	@Mock
	private SomeDao someDaoMock;

	private SomeService someService;

	@Before
	public void setUp() {
		someService = new SomeService(someDaoMock);
	}

	@Test
	public void testGetData() throws Exception {
		final Object data = new Object();
		expect(someDaoMock.getSomeData()).andReturn(data);

		replayAll();

		assertSame(data, someService.getData());

		verifyAll();
	}
}
