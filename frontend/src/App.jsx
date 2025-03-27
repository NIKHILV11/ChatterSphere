import { useState } from "react";
import JoinCreateChat from "./pages/JoinCreateChat";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <JoinCreateChat />
    </div>
  );
}

export default App;