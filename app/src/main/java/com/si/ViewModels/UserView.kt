import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.si.Models.User
import com.si.R

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList: List<User> = emptyList()

    fun setUserList(users: List<User>) {
        userList = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val codigoTextView: TextView = itemView.findViewById(R.id.text_codigo)
        private val nomeTextView: TextView = itemView.findViewById(R.id.text_nome)
        private val cpfTextView: TextView = itemView.findViewById(R.id.text_cpf)
        private val enderecoTextView: TextView = itemView.findViewById(R.id.text_endereco)
        private val telefoneTextView: TextView = itemView.findViewById(R.id.text_telefone)

        fun bind(user: User) {
            codigoTextView.text = "Código: ${user.codigo}"
            nomeTextView.text = "Nome: ${user.nome}"
            cpfTextView.text = "CPF: ${formatCpf(user.cpf)}"
            enderecoTextView.text = "Endereço: ${user.endereco}"
            telefoneTextView.text = "Telefone: ${user.telefone}"
        }
        private fun formatCpf(cpf: String): String {
            val cpfLength = cpf.length
            val asterisks = "*".repeat(cpfLength - 4)
            val formattedCpf = asterisks + cpf.substring(cpfLength - 4)
            return formattedCpf.replace("(.{3})(?=.)".toRegex(), "$1.")
        }
    }
}
