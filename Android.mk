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
fakestore_out   := $(TARGET_COMMON_OUT_ROOT)/obj/APPS/$(LOCAL_MODULE)_intermediates
fakestore_build := $(fakestore_root)/$(fakestore_dir)/build
fakestore_apk   := build/outputs/apk/release/fake-store-release-unsigned.apk

.PHONY: preps_fs
preps_fs:
	rm -rf $(fakestore_build); \
	mkdir -p $(ANDROID_BUILD_TOP)/$(fakestore_out); \
	ln -s $(ANDROID_BUILD_TOP)/$(fakestore_out)/ $(ANDROID_BUILD_TOP)/$(fakestore_build)

$(fakestore_root)/$(fakestore_dir)/$(fakestore_apk): preps_fs
	echo "sdk.dir=$(ANDROID_HOME)" > $(fakestore_root)/local.properties; \
	cd $(fakestore_root) && git submodule update --recursive --init; \
	cd $(fakestore_dir) && JAVA_TOOL_OPTIONS="$(JAVA_TOOL_OPTIONS) -Dfile.encoding=UTF8" ../gradlew assembleRelease

LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true
LOCAL_SRC_FILES := $(fakestore_dir)/$(fakestore_apk)
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)

include $(BUILD_PREBUILT)
