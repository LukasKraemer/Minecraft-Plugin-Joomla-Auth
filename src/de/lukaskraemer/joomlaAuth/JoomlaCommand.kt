package de.lukaskraemer.joomlaAuth

import at.favre.lib.crypto.bcrypt.BCrypt

class JoomlaCommand {
    companion object{
        fun checkPasswort(input: String, hash: String): Boolean {
            val result = BCrypt.verifyer().verify(input.toCharArray(), hash);
            return result.verified
        }
    }
}