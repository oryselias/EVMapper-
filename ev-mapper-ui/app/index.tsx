import { View } from "react-native";
import MapScreen from "../src/screens/MapScreen";

export default function App() {
  return (
    <View style={{ flex: 1, height: "100vh", width: "100vw" }}>
      <MapScreen />
    </View>
  );
}
