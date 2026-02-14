import { TouchableOpacity, Text, StyleSheet } from "react-native";

export default function AddStationButton({ onPress }: { onPress: () => void }) {
  return (
    <TouchableOpacity style={styles.button} onPress={onPress}>
      <Text style={styles.text}>＋</Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  button: {
    position: "absolute",
    bottom: 40,
    right: 20,
    width: 60,
    height: 60,
    borderRadius: 30,
    backgroundColor: "#0A84FF",
    justifyContent: "center",
    alignItems: "center",
    elevation: 6,
  },
  text: {
    fontSize: 36,
    color: "#fff",
    marginBottom: 4,
  },
});
