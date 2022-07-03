# Elementary Telegram Bot on Webhooks

"Long polling" periodically polls the telegram server, due to this the user will feel that the bot is "slowing down",
because there will be pauses between sending a message from the user and receiving a response. 

Webhook edition receives info from Telegram API. Yes, it looks faster but there are other cases to compare.

1. You can register webhook endpoint manually by calling ``https://api.telegram.org/bot<your_token>/setWebHook?url=<your_url>`` 
In this case your URL can be any path you want. If you register your webhook endpoint using TelegramBotsApi, then your 
endpoint will look like ``https://host/callback/<path>`` , as example
> telegram.webhook-host=https://12345124.eu.ngrok.io
>
> telegram.endpoint="webhook"

 then your endpoint is ``https://12345124.eu.ngrok.io/callback/webhook``
 Yes, you can set any path to your endpoint by you don't need to edit it manually
2. Another thing. To do it in application you have to use DefaultWebhook to activate TelegramBotsApi.useWebhook. This is according to source.
3. Your endpoint have to be https. It can't be http.
4. Webhook can be set up only on ports 80, 88, 443 or 8443