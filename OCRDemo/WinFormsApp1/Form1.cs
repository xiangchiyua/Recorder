using Baidu.Aip.Ocr;
using Newtonsoft.Json.Linq;

namespace WinFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                OpenFileDialog of = new OpenFileDialog();
                of.Filter = "图片（*.png;*.jpg;*.bmp;*.jpeg）|*.png;*.jpg;*.bmp;*.jpeg";
                if (of.ShowDialog() == DialogResult.OK)
                {
                    textBox1.Text = of.FileName;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("选择图片出错", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

        }

        private void richTextBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            try
            {
                var imagePath = textBox1.Text;
                if (string.IsNullOrEmpty(imagePath))
                {
                    MessageBox.Show("请选择图片文件", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
                var apiKey = "fCvgehIJP6msJmDRxB4eNkEy";
                var apiSecretKey = "QxocsqjZ59fWc0hW4F8jXbKSQSr5565T";
                Ocr ocr = new Ocr(apiKey, apiSecretKey)
                {
                    Timeout = 6000
                };
                byte[] bt = File.ReadAllBytes(imagePath);
                #region
                Dictionary<string, object> ops = new Dictionary<string, object>();
                ops.Add("language_type", "CHN_ENG");
                ops.Add("paragraph", true);
                #endregion
                JObject jobject = ocr.GeneralBasic(bt, ops);
                if (jobject.Count > 0)
                {
                    JArray jo = (JArray)jobject["words_result"];
                    if(jo.Count > 0)
                    {
                        for (int i = 0; i < jo.Count; i++)
                        {
                            richTextBox1.Text += jo[i]["words"].ToString() + Environment.NewLine;
                        }
                    }
                    
                }
            }
            catch(Exception ex) { 
                MessageBox.Show(ex.Message,"Error", MessageBoxButtons.OK,MessageBoxIcon.Error);
            }
        }
    }
}