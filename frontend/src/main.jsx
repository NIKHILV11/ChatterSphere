import { createRoot } from 'react-dom/client'
import './index.css'
import AppRoutes from './config/routes.jsx'
import { BrowserRouter } from 'react-router-dom'
import { Toaster } from 'react-hot-toast'
import { ChatProvider } from './context/ChatContext.jsx'
createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <Toaster position="top-center" />
      <ChatProvider>
        <AppRoutes />
      </ChatProvider>
  </BrowserRouter>
)
