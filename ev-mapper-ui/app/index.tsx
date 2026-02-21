import { View, Dimensions } from "react-native";
import MapScreen from "../src/screens/MapScreen";

const { width, height } = Dimensions.get("window");

export default function App() {
  return (
    <View style={{ flex: 1, height, width }}>
      <MapScreen />
    </View>
  );
}
