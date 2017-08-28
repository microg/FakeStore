# Copyright (c) 2014-2015 μg Project Team
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := FakeStore
LOCAL_MODULE_TAGS := optional
LOCAL_PACKAGE_NAME := FakeStore

fakestore_root  := $(LOCAL_PATH)
fakestore_dir   := fake-store
fakestore_out   := $(realpath $(OUT_DIR))/target/common/obj/APPS/$(LOCAL_MODULE)_intermediates
fakestore_build := $(fakestore_root)/$(fakestore_dir)/build
fakestore_apk   := build/outputs/apk/fake-store-release-unsigned.apk

$(fakestore_root)/$(fakestore_dir)/$(fakestore_apk):
	rm -Rf $(fakestore_build)
	mkdir -p $(fakestore_out)
	ln -s $(fakestore_out) $(fakestore_build)
	echo "sdk.dir=$(ANDROID_HOME)" > $(fakestore_root)/local.properties
	cd $(fakestore_root) && git submodule update --recursive --init
	cd $(fakestore_root)/$(fakestore_dir) && JAVA_TOOL_OPTIONS="$(JAVA_TOOL_OPTIONS) -Dfile.encoding=UTF8" ../gradlew assembleRelease

LOCAL_CERTIFICATE := platform
LOCAL_SRC_FILES := $(fakestore_dir)/$(fakestore_apk)
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)

include $(BUILD_PREBUILT)
