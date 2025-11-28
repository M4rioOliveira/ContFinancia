package com.example.contfinancia

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class AcessoAoChatbot : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private val chatbotUrl = "http://192.168.152.219:5000" // REPLACE WITH YOUR ACTUAL IP

    companion object {
        private const val TAG = "ChatbotApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acesso_ao_chatbot)

        Log.d(TAG, "App started")

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        setupWebView()

        // Show loading initially
        progressBar.visibility = View.VISIBLE
        webView.visibility = View.GONE

        Log.d(TAG, "Loading URL: $chatbotUrl")
        webView.loadUrl(chatbotUrl)
    }

    private fun setupWebView() {
        val webSettings = webView.settings

        // Essential settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true

        // Mobile viewport settings
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        // Cache settings
        webSettings.cacheMode = android.webkit.WebSettings.LOAD_DEFAULT

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                Log.d(TAG, "Inincio carregamento: $url")
                progressBar.visibility = View.VISIBLE
                webView.visibility = View.GONE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                Log.d(TAG, "Carregamento Finalizado: $url")
                progressBar.visibility = View.GONE
                webView.visibility = View.VISIBLE
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                Log.e(TAG, "Erro no WebView: $errorCode - $description - $failingUrl")

                progressBar.visibility = View.GONE
                webView.visibility = View.VISIBLE

                val errorHtml = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <style>
                            body { 
                                font-family: Arial, sans-serif; 
                                padding: 40px 20px; 
                                text-align: center; 
                                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                                color: white;
                                min-height: 100vh;
                                display: flex;
                                flex-direction: column;
                                justify-content: center;
                                align-items: center;
                                margin: 0;
                            }
                            .error-container { 
                                background: white; 
                                color: #333; 
                                padding: 30px; 
                                border-radius: 15px; 
                                margin: 20px;
                                max-width: 400px;
                                box-shadow: 0 10px 30px rgba(0,0,0,0.2);
                            }
                            h2 { color: #e74c3c; margin-top: 0; }
                            ul { text-align: left; }
                            button { 
                                background: #007bff; 
                                color: white; 
                                border: none; 
                                padding: 12px 24px; 
                                border-radius: 25px; 
                                font-size: 16px; 
                                cursor: pointer;
                                margin-top: 20px;
                            }
                            .details { 
                                background: #f8f9fa; 
                                padding: 15px; 
                                border-radius: 8px; 
                                margin: 15px 0;
                                font-size: 14px;
                                text-align: left;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="error-container">
                            <h2>Erro de Conexão</h2>
                            <p>Não foi possivel se conectar ao servidor do chatbot.</p>
                            
                            <div class="details">
                                <p><strong>Erro:</strong></p>
                                <p>Code: $errorCode</p>
                                <p>${description ?: "Erro Desconhecido"}</p>
                                <p><strong>URL:</strong> $failingUrl</p>
                            </div>
                            
                            <div style="text-align: left;">
                                <p><strong>Por favor, verifique se:</strong></p>
                                <ul>
                                    <li>O servidor está rodando no PC</li>
                                    <li>O IP no código está correto</li>
                                    <li>Ambos estão no mesmo wifi</li>
                                    <li>Não há bloqueio na porta 5000</li>
                                </ul>
                            </div>
                            
                            <button onclick="window.location.reload()">Try Again</button>
                        </div>
                    </body>
                    </html>
                """.trimIndent()

                view?.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null)
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}