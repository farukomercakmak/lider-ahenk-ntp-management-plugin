#!/usr/bin/python3
# -*- coding: utf-8 -*-
# Author: Ömer Çakmak <farukomercakmak@gmail.com>

from base.plugin.abstract_plugin import AbstractPlugin
import re

class ntpmanagement(AbstractPlugin):
    def __init__(self, data, context):
        super(AbstractPlugin, self).__init__()
        self.data = data
        self.context = context
        self.logger = self.get_logger()
        self.message_code = self.get_message_code()

    def handle_task(self):
        try:
            self.execute("sudo apt -y install ntpdate")
            server_address = self.data['server-address']
            (result_code, p_out, p_err) = self.execute( "ntpdate -u {0} ".format(server_address))

            if result_code == 0:
                self.logger.info("Script has run successfully")
            else:
                self.logger.error("Script could not run successfully: " + p_err)
            self.context.create_response(code=self.message_code.TASK_PROCESSED.value,
                                         message='NTP Sunucu '+server_address+' olması sağlandı',
                                         content_type=self.get_content_type().APPLICATION_JSON.value)
        except Exception as e:
            self.logger.error(str(e))
            self.context.create_response(code=self.message_code.TASK_ERROR.value,
                                         message='NTP yüklenemedi, hata oluştu: {0}'.format(str(e)))


def handle_task(task, context):
    plugin = ntpmanagement(task, context)
    plugin.handle_task()
    

