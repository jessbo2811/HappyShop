package ci553.happyshop.client.picker;

import java.io.IOException;

public class PickerController {
    public PickerModel pickerModel;

    /** 
     * @throws IOException
     */
    public void doProgressing() throws IOException {
        pickerModel.doProgressing();
    }
    /** 
     * @throws IOException
     */
    public void doCollected() throws IOException {
        pickerModel.doCollected();
    }
}
