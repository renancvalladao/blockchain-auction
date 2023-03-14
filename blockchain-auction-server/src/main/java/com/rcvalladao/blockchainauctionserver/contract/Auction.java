package com.rcvalladao.blockchainauctionserver.contract;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.4.
 */
@SuppressWarnings("rawtypes")
public class Auction extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516105cf3803806105cf83398101604081905261002f91610218565b805180518291600091610049918391602090910190610086565b5060208281015180516100629260018501920190610086565b50604091909101516002909101805460ff191660ff90921691909117905550610300565b828054610092906102c5565b90600052602060002090601f0160209004810192826100b457600085556100fa565b82601f106100cd57805160ff19168380011785556100fa565b828001600101855582156100fa579182015b828111156100fa5782518255916020019190600101906100df565b5061010692915061010a565b5090565b5b80821115610106576000815560010161010b565b634e487b7160e01b600052604160045260246000fd5b604051606081016001600160401b03811182821017156101575761015761011f565b60405290565b604051601f8201601f191681016001600160401b03811182821017156101855761018561011f565b604052919050565b600082601f83011261019e57600080fd5b81516001600160401b038111156101b7576101b761011f565b60206101cb601f8301601f1916820161015d565b82815285828487010111156101df57600080fd5b60005b838110156101fd5785810183015182820184015282016101e2565b8381111561020e5760008385840101525b5095945050505050565b60006020828403121561022a57600080fd5b81516001600160401b038082111561024157600080fd5b908301906060828603121561025557600080fd5b61025d610135565b82518281111561026c57600080fd5b6102788782860161018d565b82525060208301518281111561028d57600080fd5b6102998782860161018d565b6020830152506040830151925060ff831683146102b557600080fd5b6040810192909252509392505050565b600181811c908216806102d957607f821691505b602082108114156102fa57634e487b7160e01b600052602260045260246000fd5b50919050565b6102c08061030f6000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c80637ae42ff914610030575b600080fd5b61003861004e565b60405161004591906101fc565b60405180910390f35b6040805160608082018352808252602082015260009181019190915260006040518060600160405290816000820180546100879061024f565b80601f01602080910402602001604051908101604052809291908181526020018280546100b39061024f565b80156101005780601f106100d557610100808354040283529160200191610100565b820191906000526020600020905b8154815290600101906020018083116100e357829003601f168201915b505050505081526020016001820180546101199061024f565b80601f01602080910402602001604051908101604052809291908181526020018280546101459061024f565b80156101925780601f1061016757610100808354040283529160200191610192565b820191906000526020600020905b81548152906001019060200180831161017557829003601f168201915b50505091835250506002919091015460ff16602090910152919050565b6000815180845260005b818110156101d5576020818501810151868301820152016101b9565b818111156101e7576000602083870101525b50601f01601f19169290920160200192915050565b60208152600082516060602084015261021860808401826101af565b90506020840151601f1984830301604085015261023582826101af565b91505060ff60408501511660608401528091505092915050565b600181811c9082168061026357607f821691505b6020821081141561028457634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212207fe2b0824aac6f007b79f78dc02cf84277b98f9f3870eff5e5893627cbceb0c164736f6c63430008090033";

    public static final String FUNC_GETREQUIREMENTS = "getRequirements";

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Requirements> getRequirements() {
        final Function function = new Function(FUNC_GETREQUIREMENTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Requirements>() {}));
        return executeRemoteCallSingleValueReturn(function, Requirements.class);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, Requirements _requirements) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements));
        return deployRemoteCall(Auction.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, Requirements _requirements) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements));
        return deployRemoteCall(Auction.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements));
        return deployRemoteCall(Auction.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements));
        return deployRemoteCall(Auction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class Requirements extends DynamicStruct {
        public String vnfName;

        public String vnfType;

        public BigInteger numCpus;

        public Requirements(String vnfName, String vnfType, BigInteger numCpus) {
            super(new org.web3j.abi.datatypes.Utf8String(vnfName), 
                    new org.web3j.abi.datatypes.Utf8String(vnfType), 
                    new org.web3j.abi.datatypes.generated.Uint8(numCpus));
            this.vnfName = vnfName;
            this.vnfType = vnfType;
            this.numCpus = numCpus;
        }

        public Requirements(Utf8String vnfName, Utf8String vnfType, Uint8 numCpus) {
            super(vnfName, vnfType, numCpus);
            this.vnfName = vnfName.getValue();
            this.vnfType = vnfType.getValue();
            this.numCpus = numCpus.getValue();
        }
    }
}
